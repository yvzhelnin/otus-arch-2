package ru.yvzhelnin.otus.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.delivery.dto.CourierDto;
import ru.yvzhelnin.otus.delivery.dto.DeliveryResponseDto;
import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;
import ru.yvzhelnin.otus.delivery.service.CourierService;
import ru.yvzhelnin.otus.delivery.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final CourierService courierService;

    private final DeliveryService deliveryService;

    public DeliveryController(CourierService courierService, DeliveryService deliveryService) {
        this.courierService = courierService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/couriers")
    public List<CourierDto> getCouriers() {
        return courierService.getCouriers();
    }

    @GetMapping("/{courierId}")
    public List<DeliveryResponseDto> getCourierDeliveries(@PathVariable("courierId") long courierId) {
        return courierService.getCourierDeliveries(courierId);
    }

    @PutMapping("/{deliveryId}")
    public DeliveryStatus changeStatus(@PathVariable("deliveryId") long deliveryId,
                                       @RequestParam("status") DeliveryStatus status) {
        return deliveryService.changeStatus(deliveryId, status);
    }
}
