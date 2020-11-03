package ru.yvzhelnin.otus.delivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;
import ru.yvzhelnin.otus.delivery.model.Courier;
import ru.yvzhelnin.otus.delivery.model.DeliveryInfo;

import java.util.List;

@Repository
public interface DeliveryInfoRepository extends CrudRepository<DeliveryInfo, Long> {

    List<DeliveryInfo> findAllById(Iterable<Long> ids);

    List<DeliveryInfo> findAllByCourierAndDeliveryStatus(Courier courier, DeliveryStatus deliveryStatus);
}
