package ru.yvzhelnin.otus.hwcrud.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.model.Client;
import ru.yvzhelnin.otus.hwcrud.repository.ClientRepository;
import ru.yvzhelnin.otus.hwcrud.service.ClientService;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UUID createClient(ClientRequestDto clientRequestDto) {
        final UUID clientId = UUID.randomUUID();
        final Client client = new Client();
        client.setId(clientId);
        client.setUsername(clientRequestDto.getUsername());
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setEmail(clientRequestDto.getEmail());
        client.setPhone(clientRequestDto.getPhone());

        return clientRepository.save(client).getId();
    }

    @Override
    public ClientResponseDto updateClient(UUID clientId, ClientRequestDto clientRequestDto) throws ClientNotFoundException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        if (clientRequestDto.getUsername() != null) {
            client.setUsername(clientRequestDto.getUsername());
        }
        if (clientRequestDto.getFirstName() != null) {
            client.setFirstName(clientRequestDto.getFirstName());
        }
        if (clientRequestDto.getLastName() != null) {
            client.setLastName(clientRequestDto.getLastName());
        }
        if (clientRequestDto.getEmail() != null) {
            client.setEmail(clientRequestDto.getEmail());
        }
        if (clientRequestDto.getPhone() != null) {
            client.setPhone(clientRequestDto.getPhone());
        }
        var updatedClient = clientRepository.save(client);

        return new ClientResponseDto(updatedClient.getId(),
                updatedClient.getUsername(),
                updatedClient.getFirstName(),
                updatedClient.getLastName(),
                updatedClient.getEmail(),
                updatedClient.getPhone());
    }

    @Override
    public void deleteClient(UUID clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public ClientResponseDto getClient(UUID clientId) throws ClientNotFoundException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));

        return new ClientResponseDto(client.getId(),
                client.getUsername(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone());
    }
}
