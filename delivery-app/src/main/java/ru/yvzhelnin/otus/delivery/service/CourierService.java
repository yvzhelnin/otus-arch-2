package ru.yvzhelnin.otus.delivery.service;

import ru.yvzhelnin.otus.delivery.model.Courier;

import java.time.LocalDateTime;

public interface CourierService {

    Courier findFreeCourier(LocalDateTime from, LocalDateTime till);
}
