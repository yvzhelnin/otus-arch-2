package ru.yvzhelnin.otus.catalog.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.catalog.enums.EquipmentCategory;
import ru.yvzhelnin.otus.catalog.enums.SeasonType;
import ru.yvzhelnin.otus.catalog.model.catalog.Position;
import ru.yvzhelnin.otus.catalog.repository.catalog.PositionRepository;
import ru.yvzhelnin.otus.catalog.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Page<Position> findBySeasonType(PageRequest pageRequest, SeasonType seasonType) {
        return positionRepository.findAllBySeasonType(pageRequest, seasonType);
    }

    @Override
    public Page<Position> findByEquipmentCategory(PageRequest pageRequest, EquipmentCategory equipmentCategory) {
        return positionRepository.findAllByEquipmentCategory(pageRequest, equipmentCategory);
    }

    @Override
    public Page<Position> findByName(PageRequest pageRequest, String name) {
        return positionRepository.findAllByNameContainingIgnoreCase(pageRequest, name);
    }
}
