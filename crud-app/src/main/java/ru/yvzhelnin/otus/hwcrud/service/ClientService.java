package ru.yvzhelnin.otus.hwcrud.service;

import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.exception.PermissionDeniedException;

public interface ClientService {

    ClientResponseDto updateClient(String clientId, ClientRequestDto clientRequestDto) throws ClientNotFoundException, PermissionDeniedException;

    void deleteClient(String clientId) throws ClientNotFoundException, PermissionDeniedException;

    ClientResponseDto getClient(String clientId) throws ClientNotFoundException, PermissionDeniedException;
}
