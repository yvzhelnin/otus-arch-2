package ru.yvzhelnin.otus.hwcrud.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.exception.PermissionDeniedException;
import ru.yvzhelnin.otus.hwcrud.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private static final String CLIENT_ID_HEADER = "X-Client-Id";

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/me")
    public ClientResponseDto getMyProfile(@RequestHeader(CLIENT_ID_HEADER) String clientId) throws PermissionDeniedException, ClientNotFoundException {
        return clientService.getClient(clientId);
    }

    @PutMapping("/me")
    public ClientResponseDto updateMyProfile(@RequestHeader(CLIENT_ID_HEADER) String clientId,
                                             @RequestBody ClientRequestDto requestDto) throws ClientNotFoundException, PermissionDeniedException {
        return clientService.updateClient(clientId, requestDto);
    }

    @GetMapping("/{clientId}")
    public ClientResponseDto getClient(@PathVariable("clientId") String clientId) throws ClientNotFoundException, PermissionDeniedException {
        return clientService.getClient(clientId);
    }

    @PutMapping("/{clientId}")
    public ClientResponseDto updateClient(@PathVariable("clientId") String clientId,
                                          @RequestBody ClientRequestDto requestDto) throws ClientNotFoundException, PermissionDeniedException {
        return clientService.updateClient(clientId, requestDto);
    }

    @DeleteMapping("/{clientId}")
    public String deleteClient(@PathVariable("clientId") String clientId) throws PermissionDeniedException, ClientNotFoundException {
        clientService.deleteClient(clientId);

        return "Клиент с идентификатором " + clientId + " был удалён";
    }
}
