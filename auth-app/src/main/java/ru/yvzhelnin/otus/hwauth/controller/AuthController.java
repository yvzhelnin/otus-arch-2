package ru.yvzhelnin.otus.hwauth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.hwauth.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwauth.exception.AuthenticationException;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwauth.service.AuthService;
import ru.yvzhelnin.otus.hwauth.service.ClientService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final ClientService clientService;

    public AuthController(AuthService authService, ClientService clientService) {
        this.authService = authService;
        this.clientService = clientService;
    }

    @GetMapping("/check")
    public String auth() {
        return "OK";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("X-Username") String username, @RequestHeader("X-Password") String password)
            throws AuthenticationException, ClientNotFoundException {
        var jwt = authService.authenticate(username, password);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", jwt.getJwtToken());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Successfully logged in");
    }

    @GetMapping("/signin")
    public String signIn() {
        return "{\"message\": \"Please go to login and provide Login/Password\"}";
    }

    @PostMapping("/register")
    public String register(@RequestBody ClientRequestDto requestDto) {
        return clientService.createClient(requestDto);
    }
}
