package ru.yvzhelnin.otus.hwcrud.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Immutable
@Getter
public class ClientRequestDto {

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;

    public ClientRequestDto(String username, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
