package ru.yvzhelnin.otus.catalog.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.catalog.model.catalog.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
}
