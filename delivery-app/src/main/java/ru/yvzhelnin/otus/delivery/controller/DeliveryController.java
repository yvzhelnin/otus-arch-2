package ru.yvzhelnin.otus.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.delivery.dto.CourierDto;
import ru.yvzhelnin.otus.delivery.dto.DeliveryResponseDto;
import ru.yvzhelnin.otus.delivery.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final CourierService courierService;

    public DeliveryController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping("/couriers")
    public List<CourierDto> getCouriers() {
        return courierService.getCouriers();
    }

    @GetMapping("/{}")
    public List<DeliveryResponseDto> getCourierDeliveries(@PathVariable("courierId") long courierId) {
        return courierService.getCourierDeliveries(courierId);
    }
}
