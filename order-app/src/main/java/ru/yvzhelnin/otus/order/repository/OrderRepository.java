package ru.yvzhelnin.otus.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.order.model.Order;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    Order findByClientIdAndCost(String clientId, BigDecimal cost);

    List<Order> findAllByClientId(String clientId);
}
