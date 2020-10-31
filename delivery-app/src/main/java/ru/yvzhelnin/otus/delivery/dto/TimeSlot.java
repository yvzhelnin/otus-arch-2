package ru.yvzhelnin.otus.delivery.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yvzhelnin.otus.delivery.exception.DeliveryServiceException;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TimeSlot implements Comparable<TimeSlot> {

    private final long courierId;
    private final LocalDateTime from;
    private final LocalDateTime till;

    public TimeSlot(long courierId, LocalDateTime from, LocalDateTime till) {
        this.courierId = courierId;
        this.from = from;
        this.till = till;
    }

    @Override
    public int compareTo(TimeSlot timeSlot) {
        if (timeSlot.getFrom().isEqual(this.from) || timeSlot.getTill().isEqual(this.till)) {
            return 0;
        } else if (timeSlot.getFrom().isBefore(this.from) && timeSlot.getTill().isBefore(this.till)) {
            return 1;
        } else if (timeSlot.getFrom().isAfter(this.from) && timeSlot.getTill().isAfter(this.till)) {
            return -1;
        }
        throw new DeliveryServiceException("Bad time slot " + timeSlot);
    }
}
