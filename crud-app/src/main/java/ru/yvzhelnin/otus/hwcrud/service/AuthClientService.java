package ru.yvzhelnin.otus.hwcrud.service;

import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.model.Client;

public interface AuthClientService {

    Client getAuthenticatedClient() throws ClientNotFoundException;
}
