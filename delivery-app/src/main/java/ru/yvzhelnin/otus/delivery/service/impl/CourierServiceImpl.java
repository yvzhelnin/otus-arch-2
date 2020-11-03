package ru.yvzhelnin.otus.delivery.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.delivery.dto.CourierDto;
import ru.yvzhelnin.otus.delivery.dto.DeliveryResponseDto;
import ru.yvzhelnin.otus.delivery.dto.TimeSlot;
import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;
import ru.yvzhelnin.otus.delivery.exception.DeliveryServiceException;
import ru.yvzhelnin.otus.delivery.model.Courier;
import ru.yvzhelnin.otus.delivery.model.CustomerData;
import ru.yvzhelnin.otus.delivery.repository.CourierRepository;
import ru.yvzhelnin.otus.delivery.repository.DeliveryInfoRepository;
import ru.yvzhelnin.otus.delivery.service.CourierService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourierServiceImpl implements CourierService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourierServiceImpl.class);

    private final CourierRepository courierRepository;

    private final DeliveryInfoRepository deliveryInfoRepository;

    public CourierServiceImpl(CourierRepository courierRepository, DeliveryInfoRepository deliveryInfoRepository) {
        this.courierRepository = courierRepository;
        this.deliveryInfoRepository = deliveryInfoRepository;
    }

    @Override
    public Courier findFreeCourier() {
        LOGGER.info("Looking for a free courier");
        Collection<Courier> allCouriers = courierRepository.findAll();
        Map<Long, List<TimeSlot>> timeSlots = allCouriers.stream()
                .map(courier -> courier.getDeliveryInfos()
                        .stream()
                        .map(deliveryInfo -> new TimeSlot(courier.getId(), deliveryInfo.getDeliverFrom(), deliveryInfo.getDeliverTill()))
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(TimeSlot::getCourierId, Collectors.toList()));
        LOGGER.info("Assembled time slots by couriers: {}", timeSlots);
        final long availableCourierId = timeSlots.entrySet().stream()
                .filter(entry -> entry.getValue().size() < 12)
                .findAny()
                .orElseThrow(() -> new DeliveryServiceException("No available couriers found!"))
                .getKey();

        return courierRepository.findById(availableCourierId)
                .orElseThrow(() -> new DeliveryServiceException("No courier found by id = " + availableCourierId));
    }

    @Override
    public Courier findFreeCourier(LocalDateTime from, LocalDateTime till) {
        LOGGER.info("Looking for a free courier at time slot from {} till {}", from, till);
        Collection<Courier> allCouriers = courierRepository.findAll();
        Map<Long, List<TimeSlot>> timeSlots = allCouriers.stream()
                .map(courier -> courier.getDeliveryInfos()
                        .stream()
                        .map(deliveryInfo -> new TimeSlot(courier.getId(), deliveryInfo.getDeliverFrom(), deliveryInfo.getDeliverTill()))
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(TimeSlot::getCourierId, Collectors.toList()));
        LOGGER.info("Assembled time slots by couriers: {}", timeSlots);
        final long availableCourierId = findCourierId(timeSlots, from, till);

        return courierRepository.findById(availableCourierId)
                .orElseThrow(() -> new DeliveryServiceException("No courier found by id = " + availableCourierId));
    }

    private long findCourierId(Map<Long, List<TimeSlot>> timeSlots, LocalDateTime from, LocalDateTime till) {
        for (Long courierId : timeSlots.keySet()) {
            List<TimeSlot> slots = timeSlots.get(courierId);
            slots.sort(TimeSlot::compareTo);
            ListIterator<TimeSlot> iterator = slots.listIterator();
            while (iterator.hasNext()) {
                TimeSlot current = iterator.next();
                if (!iterator.hasPrevious() && till.isBefore(current.getFrom())) {
                    return courierId;
                } else if (iterator.hasNext()
                        && from.isAfter(slots.get(iterator.previousIndex()).getTill())
                        && till.isBefore(slots.get(iterator.nextIndex()).getFrom())) {
                    return courierId;
                } else if (!iterator.hasNext() && from.isAfter(slots.get(iterator.previousIndex()).getTill())) {
                    return courierId;
                }
            }
        }
        throw new DeliveryServiceException("No available couriers found!");
    }

    @Override
    public List<CourierDto> getCouriers() {
        return courierRepository.findAll().stream()
                .map(courier -> new CourierDto(courier.getId(), courier.getFirstName(), courier.getLastName(), courier.getPhone()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryResponseDto> getCourierDeliveries(long courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new DeliveryServiceException("No courier with id = " + courierId + " found"));

        return deliveryInfoRepository.findAllByCourierAndDeliveryStatus(courier, DeliveryStatus.SCHEDULED).stream()
                .map(deliveryInfo -> {
                    CustomerData customerData = deliveryInfo.getCustomerData();

                    return new DeliveryResponseDto(deliveryInfo.getId(),
                            deliveryInfo.getDeliveryType(),
                            customerData.getFullName(),
                            customerData.getPhone(),
                            customerData.getAddress(),
                            deliveryInfo.getDeposit(),
                            deliveryInfo.getDeliverFrom(),
                            deliveryInfo.getDeliverTill());
                })
                .collect(Collectors.toList());
    }
}
