package ru.yvzhelnin.otus.hwauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.hwauth.dto.ClientRequestDto;
import ru.yvzhelnin.otus.hwauth.exception.AuthenticationException;
import ru.yvzhelnin.otus.hwauth.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwauth.model.Client;
import ru.yvzhelnin.otus.hwauth.service.AuthService;
import ru.yvzhelnin.otus.hwauth.service.ClientService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private static final String SESSION_ID_COOKIE = "sessionId";
    private static final String CLIENT_ID_HEADER = "X-Client-Id";

    private static final int MAX_SESSION_SECONDS = 600;

    private final AuthService authService;

    private final ClientService clientService;

    public AuthController(AuthService authService, ClientService clientService) {
        this.authService = authService;
        this.clientService = clientService;
    }

    @GetMapping("/sessions")
    public Map<String, Client> getSessions() {
        return authService.getSessions();
    }

    @PostMapping("/register")
    public Client register(@RequestBody ClientRequestDto requestDto) {
        return clientService.createClient(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("X-Username") String username,
                                        @RequestHeader("X-Password") String password,
                                        HttpServletResponse response) throws AuthenticationException, ClientNotFoundException {
        final String sessionId = authService.createSession(username, password);
        Cookie cookie = new Cookie(SESSION_ID_COOKIE, sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(MAX_SESSION_SECONDS);
        response.addCookie(cookie);

        return ResponseEntity.ok().body("Successfully logged in");
    }

    @GetMapping("/signin")
    public String signIn() {
        return "{\"message\": \"Please go to login and provide Login/Password\"}";
    }

    @RequestMapping("/auth")
    public ResponseEntity<String> auth(@CookieValue(SESSION_ID_COOKIE) String sessionId) throws AuthenticationException {
        LOGGER.info("Performing auth for session with id = '" + sessionId + "'");
        final Client client = authService.getSessionClient(sessionId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(CLIENT_ID_HEADER, client.getId());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(sessionId);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(SESSION_ID_COOKIE) String sessionId, HttpServletResponse response) {
        authService.logout(sessionId);
        Cookie cookie = new Cookie(SESSION_ID_COOKIE, null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().body("Logged out");
    }
}
