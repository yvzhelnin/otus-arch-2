package ru.yvzhelnin.otus.billing.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceDto {

    private final BigDecimal balance;

    public BalanceDto(BigDecimal balance) {
        this.balance = balance;
    }
}
