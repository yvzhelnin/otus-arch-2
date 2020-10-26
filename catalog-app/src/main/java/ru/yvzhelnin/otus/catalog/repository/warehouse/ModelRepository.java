package ru.yvzhelnin.otus.catalog.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.catalog.model.warehouse.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    List<Model> findAllByLoadedToCatalog(boolean isLoadedToCatalog);
}
