package ru.yvzhelnin.otus.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.warehouse.dto.EquipmentDto;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentStatus;
import ru.yvzhelnin.otus.warehouse.service.EquipmentService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public void addEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {
        equipmentService.addEquipment(equipmentDto.getModelArticle(), equipmentDto.getAmount());
    }

    @PutMapping("/{inventoryNumber}")
    public EquipmentStatus changeEquipmentStatus(@PathVariable("inventoryNumber") long inventoryNumber,
                                                 @RequestParam("newStatus") EquipmentStatus newStatus) {
        return equipmentService.changeStatus(inventoryNumber, newStatus);
    }

    @PutMapping("/{customerPhone}")
    public EquipmentStatus changeEquipmentStatus(@PathVariable("customerPhone") String customerPhone,
                                                 @RequestParam("newStatus") EquipmentStatus newStatus) {
        return equipmentService.changeStatus(customerPhone, newStatus);
    }

    @GetMapping("/balance")
    public Map<String, Long> getBalance() {
        return equipmentService.getBalance();
    }
}
