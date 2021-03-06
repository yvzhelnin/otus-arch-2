package ru.yvzhelnin.otus.hwcrud.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Immutable
@Getter
public class ClientResponseDto {

    private final String clientId;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;

    public ClientResponseDto(String clientId, String username, String firstName, String lastName, String email, String phone) {
        this.clientId = clientId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
