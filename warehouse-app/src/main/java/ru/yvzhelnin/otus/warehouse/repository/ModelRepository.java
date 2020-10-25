package ru.yvzhelnin.otus.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.warehouse.model.Brand;
import ru.yvzhelnin.otus.warehouse.model.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findByName(String name);

    List<Model> findAllByBrand(Brand brand);
}
