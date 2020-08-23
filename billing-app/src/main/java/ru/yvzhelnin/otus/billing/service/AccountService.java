package ru.yvzhelnin.otus.billing.service;

import ru.yvzhelnin.otus.billing.exception.AccountNotFoundException;
import ru.yvzhelnin.otus.billing.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.billing.exception.NotEnoughMoneyException;

import java.math.BigDecimal;

public interface AccountService {

    void createAccount(String clientId) throws ClientNotFoundException;

    BigDecimal deposit(String clientId, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException;

    BigDecimal withdraw(String clientId, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException, NotEnoughMoneyException;

    BigDecimal getBalance(String clientId) throws ClientNotFoundException, AccountNotFoundException;
}
