package ru.yvzhelnin.otus.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.order.dto.notification.NotificationType;
import ru.yvzhelnin.otus.order.service.NotificationService;
import ru.yvzhelnin.otus.order.service.OrderService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    public static final String CLIENT_ID_HEADER = "X-Client-Id";

    @Value("${order.billing-app.url.withdraw}")
    private String billingAppUrl;

    private final NotificationService notificationService;

    public OrderServiceImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void placeOrder(String clientId, BigDecimal cost) {
        final String url = billingAppUrl + "/" + clientId;
        LOGGER.info("Assembled URL for money withdrawing: '{}'", url);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(CLIENT_ID_HEADER, clientId);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sum", cost.toString());
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, parameters);

        LOGGER.info("Status: {}, sending the notification", response.getStatusCode());
        if (response.getStatusCode() == HttpStatus.OK) {
            notificationService.sendNotification(NotificationType.SUCCESS, clientId, cost);
        } else {
            notificationService.sendNotification(NotificationType.FAIL, clientId, cost);
        }
    }
}
