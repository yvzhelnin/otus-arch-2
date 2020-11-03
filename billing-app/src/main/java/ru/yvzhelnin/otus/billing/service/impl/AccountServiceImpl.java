package ru.yvzhelnin.otus.billing.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.billing.enums.WithdrawResultType;
import ru.yvzhelnin.otus.billing.exception.AccountNotFoundException;
import ru.yvzhelnin.otus.billing.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.billing.model.Account;
import ru.yvzhelnin.otus.billing.model.Client;
import ru.yvzhelnin.otus.billing.repository.AccountRepository;
import ru.yvzhelnin.otus.billing.repository.ClientRepository;
import ru.yvzhelnin.otus.billing.service.AccountService;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final ClientRepository clientRepository;

    private final AccountRepository accountRepository;

    public AccountServiceImpl(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(String clientId) throws ClientNotFoundException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        Account account = new Account(UUID.randomUUID().toString(), client, BigDecimal.ZERO);
        accountRepository.save(account);
    }

    @Override
    public BigDecimal deposit(String clientId, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        Account account = accountRepository.findByClient(client)
                .orElseThrow(() -> new AccountNotFoundException("Для клиента с идентификатором " + clientId + " не найден лицевой счёт"));
        final BigDecimal newBalance = account.getBalance().add(sum);
        account.setBalance(newBalance);
        accountRepository.save(account);

        return newBalance;
    }

    @Override
    public WithdrawResultType withdraw(String clientId, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));

        return withdraw(client, sum);
    }

    @Override
    public WithdrawResultType withdrawByPhone(String clientPhoneNumber, BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException {
        final Client client = clientRepository.findByPhone(clientPhoneNumber)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с номером телефона " + clientPhoneNumber + " не найден"));

        return withdraw(client, sum);
    }

    private WithdrawResultType withdraw(Client client, BigDecimal sum) throws AccountNotFoundException {
        Account account = accountRepository.findByClient(client)
                .orElseThrow(() -> new AccountNotFoundException("Для клиента с идентификатором " + client.getId() + " не найден лицевой счёт"));
        final BigDecimal newBalance = account.getBalance().subtract(sum);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            return WithdrawResultType.NOT_ENOUGH_MONEY;
        }
        account.setBalance(newBalance);
        accountRepository.save(account);

        return WithdrawResultType.OK;
    }

    @Override
    public BigDecimal getBalance(String clientId) throws ClientNotFoundException, AccountNotFoundException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        Account account = accountRepository.findByClient(client)
                .orElseThrow(() -> new AccountNotFoundException("Для клиента с идентификатором " + clientId + " не найден лицевой счёт"));

        return account.getBalance();
    }
}
