package ru.yvzhelnin.otus.delivery.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.delivery.service.BillingService;

@Service
public class BillingServiceImpl implements BillingService {

    private final RestTemplate restTemplate;

    public BillingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void returnDeposit() {

    }
}
