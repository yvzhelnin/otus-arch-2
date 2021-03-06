package ru.yvzhelnin.otus.billing.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.billing.dto.BalanceDto;
import ru.yvzhelnin.otus.billing.enums.WithdrawResultType;
import ru.yvzhelnin.otus.billing.exception.AccountNotFoundException;
import ru.yvzhelnin.otus.billing.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.billing.exception.PermissionDeniedException;
import ru.yvzhelnin.otus.billing.service.AccountService;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private static final String CLIENT_ID_HEADER = "X-Client-Id";
    private static final String WITHDRAW_RESULT_HEADER = "X-Withdraw-Result";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<String> createAccount(@RequestHeader(CLIENT_ID_HEADER) String clientIdHeaderValue,
                                                @PathVariable("clientId") String clientId) throws ClientNotFoundException, PermissionDeniedException {
        if (!Objects.equals(clientIdHeaderValue, clientId)) {
            throw new PermissionDeniedException("Недостаточно прав для действия с аккаунтом другого пользователя");
        }
        accountService.createAccount(clientId);

        return ResponseEntity.status(HttpStatus.OK).body("Account has been created");
    }

    @PutMapping("/balance/{clientId}")
    public BalanceDto deposit(@RequestHeader(CLIENT_ID_HEADER) String clientIdHeaderValue,
                              @PathVariable("clientId") String clientId,
                              @RequestParam("sum") BigDecimal sum) throws ClientNotFoundException, AccountNotFoundException, PermissionDeniedException {
        if (!Objects.equals(clientIdHeaderValue, clientId)) {
            throw new PermissionDeniedException("Недостаточно прав для действия с аккаунтом другого пользователя");
        }
        return new BalanceDto(accountService.deposit(clientId, sum));
    }

    @PostMapping("/balance/{clientId}")
    public ResponseEntity withdraw(@RequestHeader(CLIENT_ID_HEADER) String clientIdHeaderValue,
                               @PathVariable("clientId") String clientId,
                               @RequestParam("sum") BigDecimal sum)
            throws ClientNotFoundException, AccountNotFoundException, PermissionDeniedException {
        if (!Objects.equals(clientIdHeaderValue, clientId)) {
            throw new PermissionDeniedException("Недостаточно прав для действия с аккаунтом другого пользователя");
        }
        WithdrawResultType result = accountService.withdraw(clientId, sum);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(WITHDRAW_RESULT_HEADER, result.name());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(result);
    }

    @GetMapping("/balance/{clientId}")
    public BalanceDto getBalance(@RequestHeader(CLIENT_ID_HEADER) String clientIdHeaderValue,
                                 @PathVariable("clientId") String clientId) throws ClientNotFoundException, AccountNotFoundException, PermissionDeniedException {
        if (!Objects.equals(clientIdHeaderValue, clientId)) {
            throw new PermissionDeniedException("Недостаточно прав для действия с аккаунтом другого пользователя");
        }
        return new BalanceDto(accountService.getBalance(clientId));
    }
}
