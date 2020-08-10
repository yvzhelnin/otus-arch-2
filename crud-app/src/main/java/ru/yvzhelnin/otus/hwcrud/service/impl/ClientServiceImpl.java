package ru.yvzhelnin.otus.hwcrud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.exception.PermissionDeniedException;
import ru.yvzhelnin.otus.hwcrud.model.Client;
import ru.yvzhelnin.otus.hwcrud.repository.ClientRepository;
import ru.yvzhelnin.otus.hwcrud.service.AuthClientService;
import ru.yvzhelnin.otus.hwcrud.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final AuthClientService authClientService;

    public ClientServiceImpl(ClientRepository clientRepository, AuthClientService authClientService) {
        this.clientRepository = clientRepository;
        this.authClientService = authClientService;
    }

    @Transactional
    @Override
    public ClientResponseDto updateClient(String clientId, ClientRequestDto clientRequestDto) throws ClientNotFoundException, PermissionDeniedException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        final Client authClient = authClientService.getAuthenticatedClient();
        if (!client.equals(authClient)) {
            throw new PermissionDeniedException("Недостаточно прав для изменения другого клиента!");
        }
        if (clientRequestDto.getUsername() != null) {
            client.setUsername(clientRequestDto.getUsername());
        }
        if (clientRequestDto.getPassword() != null) {
            client.setPassword(clientRequestDto.getPassword());
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

    @Transactional
    @Override
    public void deleteClient(String clientId) throws ClientNotFoundException, PermissionDeniedException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        final Client authClient = authClientService.getAuthenticatedClient();
        if (!client.equals(authClient)) {
            throw new PermissionDeniedException("Недостаточно прав для удаления другого клиента!");
        }
        clientRepository.deleteById(clientId);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientResponseDto getClient(String clientId) throws ClientNotFoundException, PermissionDeniedException {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        final Client authClient = authClientService.getAuthenticatedClient();
        if (!client.equals(authClient)) {
            throw new PermissionDeniedException("Недостаточно прав для получения данных другого клиента!");
        }
        return new ClientResponseDto(client.getId(),
                client.getUsername(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone());
    }

    @Transactional(readOnly = true)
    @Override
    public ClientResponseDto getMyself() throws ClientNotFoundException {
        final Client client = authClientService.getAuthenticatedClient();
        return new ClientResponseDto(client.getId(),
                client.getUsername(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone());
    }

    @Override
    public ClientResponseDto updateMyself(ClientRequestDto clientRequestDto) throws ClientNotFoundException {
        final Client client = authClientService.getAuthenticatedClient();
        if (clientRequestDto.getUsername() != null) {
            client.setUsername(clientRequestDto.getUsername());
        }
        if (clientRequestDto.getPassword() != null) {
            client.setPassword(clientRequestDto.getPassword());
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
}
