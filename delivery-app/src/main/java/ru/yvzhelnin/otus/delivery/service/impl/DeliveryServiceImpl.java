package ru.yvzhelnin.otus.delivery.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;
import ru.yvzhelnin.otus.delivery.exception.DeliveryServiceException;
import ru.yvzhelnin.otus.delivery.model.DeliveryInfo;
import ru.yvzhelnin.otus.delivery.repository.DeliveryInfoRepository;
import ru.yvzhelnin.otus.delivery.service.DeliveryService;
import ru.yvzhelnin.otus.delivery.service.rabbit.NotificationService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryInfoRepository deliveryInfoRepository;

    private final NotificationService notificationService;

    public DeliveryServiceImpl(DeliveryInfoRepository deliveryInfoRepository, NotificationService notificationService) {
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.notificationService = notificationService;
    }

    @Override
    public DeliveryStatus changeStatus(long deliveryId, DeliveryStatus status) {
        DeliveryInfo deliveryInfo = deliveryInfoRepository.findById(deliveryId)
                .orElseThrow(() -> new DeliveryServiceException("No delivery info found by id = " + deliveryId));

        switch (deliveryInfo.getDeliveryStatus()) {
            case SCHEDULED:
                if (status == DeliveryStatus.IN_ISSUING) {
                    deliveryInfo.setDeliveryStatus(status);
                    deliveryInfo = deliveryInfoRepository.save(deliveryInfo);
                    notificationService.sendNotification(deliveryInfo);

                    return deliveryInfo.getDeliveryStatus();
                }
            case IN_ISSUING:
                if (status == DeliveryStatus.ISSUED) {
                    deliveryInfo.setDeliveryStatus(status);

                    return deliveryInfoRepository.save(deliveryInfo).getDeliveryStatus();
                }
            default:
                throw new DeliveryServiceException("It's impossible to change delivery status from " + deliveryInfo.getDeliveryStatus() + " to " + status);
        }
    }
}
