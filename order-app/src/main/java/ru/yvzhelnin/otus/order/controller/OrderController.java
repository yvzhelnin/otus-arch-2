package ru.yvzhelnin.otus.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.order.exception.PermissionDeniedException;
import ru.yvzhelnin.otus.order.service.OrderService;
import ru.yvzhelnin.otus.order.service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public void placeOrder(@RequestHeader(OrderServiceImpl.CLIENT_ID_HEADER) String clientIdHeaderValue,
                           @RequestParam("clientId") String clientId,
                           @RequestParam("cost") BigDecimal cost) throws PermissionDeniedException {
        if (!Objects.equals(clientIdHeaderValue, clientId)) {
            throw new PermissionDeniedException("Невозможно создать заказ для другого пользователя");
        }
        orderService.placeOrder(clientId, cost);
    }
}
