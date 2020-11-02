package ru.yvzhelnin.otus.delivery.service.rabbit.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.delivery.model.DeliveryInfo;
import ru.yvzhelnin.otus.delivery.service.rabbit.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    private final Queue notificationQueue;

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate,
                                   @Qualifier("notificationQueue") Queue notificationQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationQueue = notificationQueue;
    }

    @Override
    public void sendNotification(DeliveryInfo deliveryInfo) {
        rabbitTemplate.convertAndSend(notificationQueue.getActualName(), "Запланирована доставка арендуемого инвентаря по адресу: " + deliveryInfo.getCustomerData().getAddress()
                + "\n с " + deliveryInfo.getDeliverFrom() + " по " + deliveryInfo.getDeliverTill()
                + ".\nКурьер " + deliveryInfo.getCourier().getFirstName() + " " + deliveryInfo.getCourier().getPhone());
    }
}
