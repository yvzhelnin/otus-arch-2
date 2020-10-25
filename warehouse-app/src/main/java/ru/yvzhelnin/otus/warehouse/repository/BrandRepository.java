package ru.yvzhelnin.otus.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.warehouse.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);
}
