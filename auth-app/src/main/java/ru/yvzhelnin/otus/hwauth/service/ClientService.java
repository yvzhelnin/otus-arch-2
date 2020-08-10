package ru.yvzhelnin.otus.hwauth.service;

import ru.yvzhelnin.otus.hwauth.dto.ClientRequestDto;

public interface ClientService {

    String createClient(ClientRequestDto clientRequestDto);
}
