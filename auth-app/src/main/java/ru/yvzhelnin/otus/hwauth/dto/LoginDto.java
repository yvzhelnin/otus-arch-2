package ru.yvzhelnin.otus.hwauth.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.validation.constraints.NotNull;

@Immutable
@Getter
public class LoginDto {

    @NotNull
    private final String username;
    @NotNull
    private final String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
