package ru.yvzhelnin.otus.billing.service;

import ru.yvzhelnin.otus.billing.enums.WithdrawResultType;
import ru.yvzhelnin.otus.billing.exception.AccountNotFoundException;
import ru.yvzhelnin.otus.billing.exception.ClientNotFoundException;

import java.math.BigDecimal;

public interface AccountService {

    void createAccount(String clientId) throws ClientNotFoundException;

    BigDecimal deposit(String clientId, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException;

    WithdrawResultType withdraw(String clientId, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException;

    WithdrawResultType withdrawByPhone(String clientPhoneNumber, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException;

    BigDecimal getBalance(String clientId) throws ClientNotFoundException, AccountNotFoundException;
}
