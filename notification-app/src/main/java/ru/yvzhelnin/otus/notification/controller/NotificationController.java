package ru.yvzhelnin.otus.notification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.notification.dto.NotificationDto;
import ru.yvzhelnin.otus.notification.service.NotificationHandlerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationHandlerService notificationHandlerService;

    public NotificationController(NotificationHandlerService notificationHandlerService) {
        this.notificationHandlerService = notificationHandlerService;
    }

    @GetMapping("/")
    public List<NotificationDto> getAll() {
        List<NotificationDto> result = new ArrayList<NotificationDto>(notificationHandlerService.getNotifications());
        result.sort(Comparator.comparing(NotificationDto::getTimestamp).reversed());

        return result;
    }
}
