package ru.yvzhelnin.otus.hwcrud.service;

import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;

public interface ClientService {

    String createClient(ClientRequestDto clientRequestDto);

    ClientResponseDto updateClient(String clientId, ClientRequestDto clientRequestDto) throws ClientNotFoundException;

    void deleteClient(String clientId);

    ClientResponseDto getClient(String clientId) throws ClientNotFoundException;
}
