package ru.yvzhelnin.otus.delivery.service;

public interface WarehouseService {

    void issueEquipment(String customerPhoneNumber);

    void returnEquipment(String customerPhoneNumber);

    void unBookEquipment(String customerPhoneNumber);
}
