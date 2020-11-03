package ru.yvzhelnin.otus.warehouse.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentStatus;
import ru.yvzhelnin.otus.warehouse.exception.WarehouseException;
import ru.yvzhelnin.otus.warehouse.model.BookingLog;
import ru.yvzhelnin.otus.warehouse.model.Equipment;
import ru.yvzhelnin.otus.warehouse.model.Model;
import ru.yvzhelnin.otus.warehouse.repository.BookingLogRepository;
import ru.yvzhelnin.otus.warehouse.repository.EquipmentRepository;
import ru.yvzhelnin.otus.warehouse.repository.ModelRepository;
import ru.yvzhelnin.otus.warehouse.service.EquipmentService;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private final ModelRepository modelRepository;

    private final EquipmentRepository equipmentRepository;

    private final BookingLogRepository bookingLogRepository;

    public EquipmentServiceImpl(ModelRepository modelRepository,
                                EquipmentRepository equipmentRepository,
                                BookingLogRepository bookingLogRepository) {
        this.modelRepository = modelRepository;
        this.equipmentRepository = equipmentRepository;
        this.bookingLogRepository = bookingLogRepository;
    }

    @Override
    public void addEquipment(String modelArticle, int amount) {
        Model model = modelRepository.findById(modelArticle)
                .orElseThrow(() -> new WarehouseException("Model with article = " + modelArticle + " not found"));
        if (amount <= 0) {
            throw new WarehouseException("Wrong amount = " + amount);
        }
        int i = 0;
        while (i < amount) {
            Equipment current = new Equipment();
            current.setModel(model);
            current.setEquipmentStatus(EquipmentStatus.AVAILABLE);
            equipmentRepository.save(current);
            i++;
        }
    }

    @Override
    public EquipmentStatus changeStatus(Long inventoryNumber, EquipmentStatus newStatus) {
        Equipment equipment = equipmentRepository.findById(inventoryNumber)
                .orElseThrow(() -> new WarehouseException("Equipment with inventory number = " + inventoryNumber + " not found"));
        EquipmentStatus currentStatus = equipment.getEquipmentStatus();
        if (currentStatus == EquipmentStatus.WRITTEN_OFF) {
            throw new WarehouseException("Status changing for written-off equipment is impossible");
        }
        switch (newStatus) {
            case WRITTEN_OFF:
                if (currentStatus == EquipmentStatus.AVAILABLE) {
                    equipment.setEquipmentStatus(EquipmentStatus.WRITTEN_OFF);
                    equipmentRepository.save(equipment);
                }
                return equipment.getEquipmentStatus();
            case ISSUED:
                if (currentStatus == EquipmentStatus.BOOKED) {
                    equipment.setEquipmentStatus(EquipmentStatus.ISSUED);
                    equipmentRepository.save(equipment);
                }
                return equipment.getEquipmentStatus();
            case AVAILABLE:
                if (currentStatus == EquipmentStatus.ISSUED) {
                    equipment.setEquipmentStatus(EquipmentStatus.AVAILABLE);
                    equipmentRepository.save(equipment);
                }
                return equipment.getEquipmentStatus();
            default:
                throw new WarehouseException("It's impossible to change status from " + currentStatus + " to " + newStatus);
        }
    }

    @Override
    public EquipmentStatus changeStatus(String customerPhone, EquipmentStatus newStatus) {
        Collection<Long> inventoryNumbers = bookingLogRepository.findAllByCustomerPhoneNumber(customerPhone).stream()
                .map(BookingLog::getEquipment)
                .map(Equipment::getInventoryNumber)
                .collect(Collectors.toList());
        inventoryNumbers.forEach(inventoryNumber -> changeStatus(inventoryNumber, newStatus));

        return newStatus;
    }

    @Override
    public Map<String, Long> getBalance() {
        return equipmentRepository.findAllByEquipmentStatus(EquipmentStatus.AVAILABLE)
                .stream()
                .map(equipment -> equipment.getModel().getArticle())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
