package ru.yvzhelnin.otus.billing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.billing.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    Optional<Client> findByPhone(String phone);
}
