package ru.yvzhelnin.otus.delivery.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;
import ru.yvzhelnin.otus.delivery.enums.DeliveryType;
import ru.yvzhelnin.otus.delivery.exception.DeliveryServiceException;
import ru.yvzhelnin.otus.delivery.model.Courier;
import ru.yvzhelnin.otus.delivery.model.DeliveryInfo;
import ru.yvzhelnin.otus.delivery.repository.DeliveryInfoRepository;
import ru.yvzhelnin.otus.delivery.service.BillingService;
import ru.yvzhelnin.otus.delivery.service.CourierService;
import ru.yvzhelnin.otus.delivery.service.DeliveryService;
import ru.yvzhelnin.otus.delivery.service.WarehouseService;
import ru.yvzhelnin.otus.delivery.service.rabbit.NotificationService;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryInfoRepository deliveryInfoRepository;

    private final NotificationService notificationService;

    private final CourierService courierService;

    private final WarehouseService warehouseService;

    private final BillingService billingService;

    public DeliveryServiceImpl(DeliveryInfoRepository deliveryInfoRepository,
                               NotificationService notificationService,
                               CourierService courierService,
                               WarehouseService warehouseService,
                               BillingService billingService) {
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.notificationService = notificationService;
        this.courierService = courierService;
        this.warehouseService = warehouseService;
        this.billingService = billingService;
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
                    warehouseService.issueEquipment(deliveryInfo.getCustomerData().getPhone());

                    return deliveryInfoRepository.save(deliveryInfo).getDeliveryStatus();
                }
            case IN_RETURNING:
                if (status == DeliveryStatus.RETURNED) {
                    deliveryInfo.setDeliveryStatus(status);
                    warehouseService.returnEquipment(deliveryInfo.getCustomerData().getPhone());
                    warehouseService.unBookEquipment(deliveryInfo.getCustomerData().getPhone());
                    billingService.returnDeposit(deliveryInfo.getCustomerData().getPhone(), deliveryInfo.getDeposit());

                    return deliveryInfoRepository.save(deliveryInfo).getDeliveryStatus();
                }
            default:
                throw new DeliveryServiceException("It's impossible to change delivery status from " + deliveryInfo.getDeliveryStatus() + " to " + status);
        }
    }

    @Override
    public void returnDeliveries(List<Long> deliveryIds) {
        List<DeliveryInfo> deliveryInfos = deliveryInfoRepository.findAllById(deliveryIds);
        deliveryInfos.forEach(deliveryInfo -> {
            Courier courier = courierService.findFreeCourier();
            deliveryInfo.setCourier(courier);
            deliveryInfo.setDeliveryType(DeliveryType.RETURN);
            deliveryInfo.setDeliveryStatus(DeliveryStatus.IN_RETURNING);
            deliveryInfo = deliveryInfoRepository.save(deliveryInfo);

            notificationService.sendNotification(deliveryInfo);
        });
    }
}
