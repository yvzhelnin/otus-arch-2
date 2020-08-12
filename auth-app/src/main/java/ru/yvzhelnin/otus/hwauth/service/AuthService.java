package ru.yvzhelnin.otus.hwauth.service;

import ru.yvzhelnin.otus.hwauth.exception.AuthenticationException;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwauth.model.Client;

import java.util.Map;

public interface AuthService {

    Client getSessionClient(String sessionId) throws AuthenticationException;

    String createSession(String username, String password) throws AuthenticationException, ClientNotFoundException;

    void logout(String sessionId);

    Map<String, Client> getSessions();
}
