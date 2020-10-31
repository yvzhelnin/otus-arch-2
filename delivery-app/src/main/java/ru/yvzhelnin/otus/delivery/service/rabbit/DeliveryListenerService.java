package ru.yvzhelnin.otus.delivery.service.rabbit;

import java.io.IOException;

public interface DeliveryListenerService {

    void receive(String message) throws IOException;
}
