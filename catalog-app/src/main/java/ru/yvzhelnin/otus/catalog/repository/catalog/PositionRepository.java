package ru.yvzhelnin.otus.catalog.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.catalog.enums.EquipmentCategory;
import ru.yvzhelnin.otus.catalog.enums.SeasonType;
import ru.yvzhelnin.otus.catalog.model.catalog.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, String>, PagingAndSortingRepository<Position, String> {

    Page<Position> findAllBySeasonType(PageRequest pageRequest, SeasonType seasonType);

    Page<Position> findAllByEquipmentCategory(PageRequest pageRequest, EquipmentCategory equipmentCategory);

    Page<Position> findAllByNameContainingIgnoreCase(PageRequest pageRequest, String name);
}
