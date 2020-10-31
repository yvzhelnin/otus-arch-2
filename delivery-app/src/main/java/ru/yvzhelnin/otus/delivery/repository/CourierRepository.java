package ru.yvzhelnin.otus.delivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.delivery.model.Courier;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {
}
