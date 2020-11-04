package ru.yvzhelnin.otus.delivery.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.delivery.service.BillingService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BillingServiceImpl implements BillingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingServiceImpl.class);

    @Value("delivery.billing-app.url.withdraw")
    private String billingUrl;

    private final RestTemplate restTemplate;

    public BillingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void returnDeposit(String clientPhoneNumber, BigDecimal depositSum) {
        BigDecimal sumToReturn = depositSum.multiply(BigDecimal.valueOf(-1));
        final String url = billingUrl + "?sum=" + depositSum + "&clientPhoneNumber=" + clientPhoneNumber;
        LOGGER.info("Assembled URL for money returning: '{}'", url);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sum", depositSum.toString());
        parameters.put("clientPhoneNumber", clientPhoneNumber);
        HttpEntity requestEntity = new HttpEntity(new HttpHeaders());
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class, parameters);
    }
}
