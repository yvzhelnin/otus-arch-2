package ru.yvzhelnin.otus.warehouse.service;

import java.util.Map;

public interface BookingService {

    void bookEquipment(Map<String, Long> models, String customerPhoneNumber);

    void unBookEquipment(String customerPhoneNumber);
}
