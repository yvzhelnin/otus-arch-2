package ru.yvzhelnin.otus.warehouse.service;

import ru.yvzhelnin.otus.warehouse.enums.EquipmentStatus;

import java.util.Map;

public interface EquipmentService {

    void addEquipment(String modelArticle, int amount);

    EquipmentStatus changeStatus(Long inventoryNumber, EquipmentStatus newStatus);

    EquipmentStatus changeStatus(String customerPhone, EquipmentStatus newStatus);

    Map<String, Long> getBalance();
}
