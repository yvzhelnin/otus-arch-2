package ru.yvzhelnin.otus.delivery.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.delivery.model.Courier;
import ru.yvzhelnin.otus.delivery.repository.CourierRepository;
import ru.yvzhelnin.otus.delivery.service.CourierService;

import java.time.LocalDateTime;

@Service
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    public CourierServiceImpl(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Override
    public Courier findFreeCourier(LocalDateTime from, LocalDateTime till) {
        return null;
    }
}
