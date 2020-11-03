package ru.yvzhelnin.otus.delivery.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.delivery.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final RestTemplate restTemplate;

    public WarehouseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void issueEquipment(String customerPhoneNumber) {

    }

    @Override
    public void returnEquipment(String customerPhoneNumber) {

    }

    @Override
    public void unBookEquipment(String customerPhoneNumber) {

    }
}
