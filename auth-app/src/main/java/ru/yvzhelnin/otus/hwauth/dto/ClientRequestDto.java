package ru.yvzhelnin.otus.hwauth.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Immutable
@Getter
public class ClientRequestDto {

    private final String username;

    private final String password;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;

    public ClientRequestDto(String username,
                            String password,
                            String firstName,
                            String lastName,
                            String email,
                            String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
