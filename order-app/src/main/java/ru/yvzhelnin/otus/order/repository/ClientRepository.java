package ru.yvzhelnin.otus.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.order.model.CustomerData;

@Repository
public interface ClientRepository extends CrudRepository<CustomerData, String> {
}
