package ru.yvzhelnin.otus.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.yvzhelnin.otus.catalog.enums.EquipmentCategory;
import ru.yvzhelnin.otus.catalog.enums.SeasonType;
import ru.yvzhelnin.otus.catalog.model.catalog.Position;

public interface PositionService {

    Page<Position> findBySeasonType(PageRequest pageRequest, SeasonType seasonType);

    Page<Position> findByEquipmentCategory(PageRequest pageRequest, EquipmentCategory equipmentCategory);

    Page<Position> findByName(PageRequest pageRequest, String name);
}
