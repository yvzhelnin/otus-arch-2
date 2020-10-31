package ru.yvzhelnin.otus.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.order.dto.BookingDto;
import ru.yvzhelnin.otus.order.service.WarehouseService;

import java.util.HashMap;
import java.util.Map;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Value("${order.warehouse-app.url.book}")
    private String warehouseAppBookUrl;

    @Value("${order.warehouse-app.url.unbook}")
    private String warehouseAppUnBookUrl;

    private final RestTemplate restTemplate;

    public WarehouseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void bookEquipment(Map<String, Long> positions, String customerPhoneNumber, String clientId) {
        LOGGER.info("Booking equipment {} for {}", positions, customerPhoneNumber);
        BookingDto bookingDto = new BookingDto(positions, customerPhoneNumber);
        Map<String, String> parameters = new HashMap<>();
        HttpEntity<BookingDto> requestEntity = new HttpEntity<>(bookingDto);

        restTemplate.exchange(warehouseAppBookUrl, HttpMethod.POST, requestEntity, Void.class, parameters);
        LOGGER.info("Equipment {} for {} has been booked", positions, customerPhoneNumber);
    }

    @Override
    public void unBookEquipment(String customerPhoneNumber) {
        LOGGER.info("Unbooking equipment for customer with number {}", customerPhoneNumber);
        final String url = warehouseAppUnBookUrl + "/" + customerPhoneNumber;
        Map<String, String> parameters = new HashMap<>();
        HttpEntity<BookingDto> requestEntity = new HttpEntity<>(new HttpHeaders());

        restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class, parameters);
        LOGGER.info("Equipment for {} has been unbooked", customerPhoneNumber);
    }
}
