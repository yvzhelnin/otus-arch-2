package ru.yvzhelnin.otus.order.service;

import java.util.Map;

public interface WarehouseService {

    void bookEquipment(Map<String, Long> positions, String customerPhoneNumber, String clientId);

    void unBookEquipment(String customerPhoneNumber);
}
