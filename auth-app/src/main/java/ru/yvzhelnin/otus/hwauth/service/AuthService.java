package ru.yvzhelnin.otus.hwauth.service;

import ru.yvzhelnin.otus.hwauth.dto.JwtResponse;
import ru.yvzhelnin.otus.hwauth.exception.AuthenticationException;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwauth.model.Client;

public interface AuthService {

    JwtResponse authenticate(String username, String password) throws ClientNotFoundException, AuthenticationException;

    Client getAuthenticatedClient() throws ClientNotFoundException;
}
