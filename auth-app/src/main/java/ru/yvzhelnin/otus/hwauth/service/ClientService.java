package ru.yvzhelnin.otus.hwauth.service;

import ru.yvzhelnin.otus.hwauth.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwauth.model.Client;

public interface ClientService {

    Client createClient(ClientRequestDto clientRequestDto);
}
