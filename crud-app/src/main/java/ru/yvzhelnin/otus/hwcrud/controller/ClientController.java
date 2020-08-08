package ru.yvzhelnin.otus.hwcrud.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.hwcrud.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwcrud.dto.ClientResponseDto;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/")
    public String createClient(@RequestBody ClientRequestDto requestDto) {
        return clientService.createClient(requestDto);
    }

    @GetMapping("/{clientId}")
    public ClientResponseDto getClient(@PathVariable("clientId") String clientId) throws ClientNotFoundException {
        return clientService.getClient(clientId);
    }

    @PutMapping("/{clientId}")
    public ClientResponseDto updateClient(@PathVariable("clientId") String clientId,
                                          @RequestBody ClientRequestDto requestDto) throws ClientNotFoundException {
        return clientService.updateClient(clientId, requestDto);
    }

    @DeleteMapping("/{clientId}")
    public String deleteClient(@PathVariable("clientId") String clientId) {
        clientService.deleteClient(clientId);

        return "Клиент с идентификатором " + clientId + " был удалён";
    }
}