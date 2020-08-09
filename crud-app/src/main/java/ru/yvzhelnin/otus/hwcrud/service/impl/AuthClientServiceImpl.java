package ru.yvzhelnin.otus.hwcrud.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.model.Client;
import ru.yvzhelnin.otus.hwcrud.repository.ClientRepository;
import ru.yvzhelnin.otus.hwcrud.service.AuthClientService;

@Service
public class AuthClientServiceImpl implements AuthClientService {

    private final ClientRepository clientRepository;

    public AuthClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getAuthenticatedClient() throws ClientNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        return clientRepository.findByUsername(username).orElseThrow(() -> new ClientNotFoundException("Пользователь не найден " + username));
    }
}
