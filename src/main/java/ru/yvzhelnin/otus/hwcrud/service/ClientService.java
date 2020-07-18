package ru.yvzhelnin.otus.hwcrud.service;

import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;

import java.util.UUID;

public interface ClientService {

    UUID createClient(ClientRequestDto clientRequestDto);

    ClientResponseDto updateClient(UUID clientId, ClientRequestDto clientRequestDto) throws ClientNotFoundException;

    void deleteClient(UUID clientId);

    ClientResponseDto getClient(UUID clientId) throws ClientNotFoundException;
}
