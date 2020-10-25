package ru.yvzhelnin.otus.warehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.warehouse.dto.EquipmentDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @PostMapping()
    public void addEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {

    }
}
