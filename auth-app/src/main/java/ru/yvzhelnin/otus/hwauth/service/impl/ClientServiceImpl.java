package ru.yvzhelnin.otus.hwauth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yvzhelnin.otus.hwauth.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwauth.model.Client;
import ru.yvzhelnin.otus.hwauth.repository.ClientRepository;
import ru.yvzhelnin.otus.hwauth.service.ClientService;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public String createClient(ClientRequestDto clientRequestDto) {
        final String clientId = UUID.randomUUID().toString();
        final Client client = new Client();
        client.setId(clientId);
        client.setUsername(clientRequestDto.getUsername());
        client.setPassword(clientRequestDto.getPassword());
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setEmail(clientRequestDto.getEmail());
        client.setPhone(clientRequestDto.getPhone());

        return clientRepository.save(client).getId();
    }
}
