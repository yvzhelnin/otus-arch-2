package ru.yvzhelnin.otus.returnal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.returnal.enums.DeliveryStatus;
import ru.yvzhelnin.otus.returnal.model.DeliveryInfo;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryInfoRepository extends CrudRepository<DeliveryInfo, Long> {

    List<DeliveryInfo> findAllByDeliveryStatusAndReturnDateAndCourierIsNull(DeliveryStatus deliveryStatus, LocalDate returnDate);
}
