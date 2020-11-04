package ru.yvzhelnin.otus.delivery.service;

import java.math.BigDecimal;

public interface BillingService {

    void returnDeposit(String clientPhoneNumber, BigDecimal depositSum);
}
