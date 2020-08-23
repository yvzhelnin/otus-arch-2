package ru.yvzhelnin.otus.notification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.notification.service.NotificationHandlerService;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final NotificationHandlerService notificationHandlerService;

    public NotificationController(NotificationHandlerService notificationHandlerService) {
        this.notificationHandlerService = notificationHandlerService;
    }

    @GetMapping("/")
    public Map<String, String> getAll() {
        return notificationHandlerService.getNotifications().entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().format(FORMATTER), Map.Entry::getValue));
    }
}
