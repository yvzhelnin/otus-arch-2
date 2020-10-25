package ru.yvzhelnin.otus.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.warehouse.model.BookingLog;

import java.util.List;

@Repository
public interface BookingLogRepository extends JpaRepository<BookingLog, Long> {

    List<BookingLog> findAllByCustomerPhoneNumber(String customerPhoneNumber);
}
