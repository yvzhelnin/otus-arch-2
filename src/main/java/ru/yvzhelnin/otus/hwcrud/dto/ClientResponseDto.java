package ru.yvzhelnin.otus.hwcrud.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import java.util.UUID;

@Immutable
@Getter
public class ClientResponseDto {

    private final UUID uuid;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;

    public ClientResponseDto(UUID uuid, String username, String firstName, String lastName, String email, String phone) {
        this.uuid = uuid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
