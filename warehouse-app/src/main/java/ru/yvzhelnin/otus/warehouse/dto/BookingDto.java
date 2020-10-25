package ru.yvzhelnin.otus.warehouse.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@ToString
public class BookingDto {

    @NotNull
    private final Map<String, Long> models;

    @NotNull
    private final String customerPhoneNumber;

    public BookingDto(@NotNull Map<String, Long> models,
                      @NotNull String customerPhoneNumber) {
        this.models = models;
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
