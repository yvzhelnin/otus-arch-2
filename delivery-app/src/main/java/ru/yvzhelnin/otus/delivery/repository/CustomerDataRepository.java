package ru.yvzhelnin.otus.delivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.delivery.model.CustomerData;

@Repository
public interface CustomerDataRepository extends CrudRepository<CustomerData, String> {
}
