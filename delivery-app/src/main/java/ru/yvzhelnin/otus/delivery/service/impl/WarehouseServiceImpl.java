package ru.yvzhelnin.otus.delivery.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.delivery.enums.EquipmentStatus;
import ru.yvzhelnin.otus.delivery.service.WarehouseService;

import java.util.HashMap;
import java.util.Map;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Value("delivery.warehouse-app.url.unbook")
    private String unbookUrl;

    @Value("delivery.warehouse-app.url.change.status")
    private String changeStatusUrl;

    private final RestTemplate restTemplate;

    public WarehouseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void issueEquipment(String customerPhoneNumber) {
        final String url = changeStatusUrl + "?customerPhone=" + customerPhoneNumber + "&newStatus=" + EquipmentStatus.ISSUED;
        LOGGER.info("Assembled URL for equipment issuing: '{}'", url);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("customerPhone", customerPhoneNumber);
        parameters.put("newStatus", EquipmentStatus.ISSUED.name());
        HttpEntity requestEntity = new HttpEntity(new HttpHeaders());
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, EquipmentStatus.class, parameters);
    }

    @Override
    public void returnEquipment(String customerPhoneNumber) {
        final String url = changeStatusUrl + "?customerPhone=" + customerPhoneNumber + "&newStatus=" + EquipmentStatus.AVAILABLE;
        LOGGER.info("Assembled URL for equipment returning: '{}'", url);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("customerPhone", customerPhoneNumber);
        parameters.put("newStatus", EquipmentStatus.AVAILABLE.name());
        HttpEntity requestEntity = new HttpEntity(new HttpHeaders());
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, EquipmentStatus.class, parameters);
    }

    @Override
    public void unBookEquipment(String customerPhoneNumber) {
        final String url = unbookUrl + "/" + customerPhoneNumber;
        LOGGER.info("Assembled URL for equipment unbooking: '{}'", url);
        HttpEntity requestEntity = new HttpEntity(new HttpHeaders());
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, new HashMap<>());
    }
}
