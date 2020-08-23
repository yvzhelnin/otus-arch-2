package ru.yvzhelnin.otus.billing.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.math.BigDecimal;

@Immutable
@Getter
@Setter
public class BalanceDto {

    private final BigDecimal balance;

    public BalanceDto(BigDecimal balance) {
        this.balance = balance;
    }
}
