package ru.yvzhelnin.otus.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourierDto {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String phone;
}
