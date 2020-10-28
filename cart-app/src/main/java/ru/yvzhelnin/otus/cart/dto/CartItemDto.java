package ru.yvzhelnin.otus.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CartItemDto implements Serializable {

    private static final long serialVersionUID = -6725271630383974806L;

    private final String positionArticle;

    private final BigDecimal pledge;

    private final BigDecimal dailyCost;

    private final int duration;
}
