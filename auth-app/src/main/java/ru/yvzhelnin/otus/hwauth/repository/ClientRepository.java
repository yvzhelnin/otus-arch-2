package ru.yvzhelnin.otus.hwauth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.hwauth.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    Optional<Client> findByUsername(String username);
}
