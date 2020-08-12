package ru.yvzhelnin.otus.hwauth.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.hwauth.exception.AuthenticationException;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwauth.model.Client;
import ru.yvzhelnin.otus.hwauth.repository.ClientRepository;
import ru.yvzhelnin.otus.hwauth.service.AuthService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;

    private final Map<String, Client> sessionStorage = new HashMap<>();

    public AuthServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getSessionClient(String sessionId) throws AuthenticationException {
        if (sessionStorage.containsKey(sessionId)) {
            return sessionStorage.get(sessionId);
        }
        throw new AuthenticationException("Session with id = '" + "' does not exist");
    }

    @Override
    public String createSession(String username, String password) throws AuthenticationException, ClientNotFoundException {
        Client client = clientRepository.findByUsername(username).orElseThrow(() -> new ClientNotFoundException("Пользователь с именем '" + username + "' не найден"));
        if (!Objects.equals(password, client.getPassword())) {
            throw new AuthenticationException("Пароли не совпадают");
        }
        String sessionId = UUID.randomUUID().toString();
        sessionStorage.put(sessionId, client);

        return sessionId;
    }

    @Override
    public void logout(String sessionId) {
        sessionStorage.remove(sessionId);
    }

    @Override
    public Map<String, Client> getSessions() {
        return Collections.unmodifiableMap(sessionStorage);
    }
}
