package ru.yvzhelnin.otus.delivery.service;

import ru.yvzhelnin.otus.delivery.dto.CourierDto;
import ru.yvzhelnin.otus.delivery.dto.DeliveryResponseDto;
import ru.yvzhelnin.otus.delivery.model.Courier;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierService {

    Courier findFreeCourier(LocalDateTime from, LocalDateTime till);

    List<CourierDto> getCouriers();

    List<DeliveryResponseDto> getCourierDeliveries(long courierId);
}
