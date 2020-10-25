package ru.yvzhelnin.otus.warehouse.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentStatus;
import ru.yvzhelnin.otus.warehouse.exception.WarehouseException;
import ru.yvzhelnin.otus.warehouse.model.BookingLog;
import ru.yvzhelnin.otus.warehouse.model.Equipment;
import ru.yvzhelnin.otus.warehouse.model.Model;
import ru.yvzhelnin.otus.warehouse.repository.BookingLogRepository;
import ru.yvzhelnin.otus.warehouse.repository.EquipmentRepository;
import ru.yvzhelnin.otus.warehouse.repository.ModelRepository;
import ru.yvzhelnin.otus.warehouse.service.BookingService;
import ru.yvzhelnin.otus.warehouse.service.EquipmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final EquipmentRepository equipmentRepository;

    private final ModelRepository modelRepository;

    private final BookingLogRepository bookingLogRepository;

    private final EquipmentService equipmentService;

    public BookingServiceImpl(EquipmentRepository equipmentRepository,
                              ModelRepository modelRepository,
                              BookingLogRepository bookingLogRepository,
                              EquipmentService equipmentService) {
        this.equipmentRepository = equipmentRepository;
        this.modelRepository = modelRepository;
        this.bookingLogRepository = bookingLogRepository;
        this.equipmentService = equipmentService;
    }

    @Override
    public void bookEquipment(Map<String, Long> models, String customerPhoneNumber) {
        Map<String, Long> balance = equipmentService.getBalance();
        List<BookingLog> bookingLogs = new ArrayList<>();
        List<Equipment> bookingList = new ArrayList<>();
        models.forEach((article, requestedAmount) -> {
            Model model = modelRepository.findById(article)
                    .orElseThrow(() -> new WarehouseException("Model with article = " + article + " not found"));
            long articleBalance = balance.getOrDefault(article, 0L);
            if (articleBalance < requestedAmount) {
                throw new WarehouseException("Not enough equipment with article " + article + " at the warehouse");
            }
            List<Equipment> currentEquipment = equipmentRepository.findAllByModelAndEquipmentStatus(model, EquipmentStatus.AVAILABLE);
            List<Equipment> forBooking = currentEquipment.subList(0, requestedAmount.intValue());
            forBooking.forEach(bookingEquipment -> {
                bookingEquipment.setEquipmentStatus(EquipmentStatus.BOOKED);
                bookingList.add(bookingEquipment);

                BookingLog bookingLog = new BookingLog();
                bookingLog.setCustomerPhoneNumber(customerPhoneNumber);
                bookingLog.setEquipment(bookingEquipment);
                bookingLogs.add(bookingLog);
            });
        });
        bookingLogRepository.saveAll(bookingLogs);
        equipmentRepository.saveAll(bookingList);
    }

    @Override
    public void unBookEquipment(String customerPhoneNumber) {
        List<BookingLog> bookingLogs = bookingLogRepository.findAllByCustomerPhoneNumber(customerPhoneNumber);
        List<Long> inventoryNumbers = bookingLogs.stream().map(e -> e.getEquipment().getInventoryNumber()).collect(Collectors.toList());
        List<Equipment> unBookingEquipment = equipmentRepository.findAllById(inventoryNumbers);
        unBookingEquipment.forEach(e -> e.setEquipmentStatus(EquipmentStatus.AVAILABLE));
        equipmentRepository.saveAll(unBookingEquipment);
        bookingLogRepository.deleteAll(bookingLogs);
    }
}
