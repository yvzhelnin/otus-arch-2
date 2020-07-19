package ru.yvzhelnin.otus.hwcrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.hwcrud.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {
}
