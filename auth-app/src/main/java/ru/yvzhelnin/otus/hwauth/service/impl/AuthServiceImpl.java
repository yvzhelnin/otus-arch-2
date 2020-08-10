package ru.yvzhelnin.otus.hwauth.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.hwauth.dto.JwtResponse;
import ru.yvzhelnin.otus.hwauth.dto.LoginDto;
import ru.yvzhelnin.otus.hwauth.exception.AuthenticationException;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwauth.model.Client;
import ru.yvzhelnin.otus.hwauth.repository.ClientRepository;
import ru.yvzhelnin.otus.hwauth.security.JwtTokenHandler;
import ru.yvzhelnin.otus.hwauth.service.AuthService;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;

    private final JwtTokenHandler jwtTokenHandler;

    public AuthServiceImpl(ClientRepository clientRepository, JwtTokenHandler jwtTokenHandler) {
        this.clientRepository = clientRepository;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    public JwtResponse authenticate(LoginDto loginDto) throws ClientNotFoundException, AuthenticationException {
        final String username = loginDto.getUsername();
        final String password = loginDto.getPassword();
        final Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new ClientNotFoundException("Client with username '" + username + "' hasn't been found!"));
        if (!Objects.equals(password, client.getPassword())) {
            throw new AuthenticationException("Entered password is incorrect!");
        }
        return jwtTokenHandler.generateToken(client);
    }
}
