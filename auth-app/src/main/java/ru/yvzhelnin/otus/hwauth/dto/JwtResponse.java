package ru.yvzhelnin.otus.hwauth.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import java.io.Serializable;

@Getter
@Immutable
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091046844L;

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
