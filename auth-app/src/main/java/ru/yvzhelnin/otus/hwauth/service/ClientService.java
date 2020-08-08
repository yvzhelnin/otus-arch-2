package ru.yvzhelnin.otus.hwauth.service;

import ru.yvzhelnin.otus.hwauth.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwauth.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;

public interface ClientService {

    String createClient(ClientRequestDto clientRequestDto);

    ClientResponseDto updateClient(String clientId, ClientRequestDto clientRequestDto) throws ClientNotFoundException;

    void deleteClient(String clientId);

    ClientResponseDto getClient(String clientId) throws ClientNotFoundException;
}
