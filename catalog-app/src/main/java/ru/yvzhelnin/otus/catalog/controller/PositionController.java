package ru.yvzhelnin.otus.catalog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.catalog.enums.EquipmentCategory;
import ru.yvzhelnin.otus.catalog.enums.SeasonType;
import ru.yvzhelnin.otus.catalog.model.catalog.Position;
import ru.yvzhelnin.otus.catalog.service.PositionService;

@RestController
@RequestMapping("/api/catalog/position")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/season")
    public Page<Position> findBySeasonType(@RequestParam("page") int page,
                                           @RequestParam("size") int size,
                                           @RequestParam("sortColumn") String sortColumn,
                                           @RequestParam("sortDirection") Sort.Direction sortDirection,
                                           @RequestParam("seasonType") SeasonType seasonType) {
        Sort sort = new Sort(sortDirection, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return positionService.findBySeasonType(pageRequest, seasonType);
    }

    @GetMapping("/category")
    public Page<Position> findByEquipmentCategory(@RequestParam("page") int page,
                                                  @RequestParam("size") int size,
                                                  @RequestParam("sortColumn") String sortColumn,
                                                  @RequestParam("sortDirection") Sort.Direction sortDirection,
                                                  @RequestParam("equipmentCategory") EquipmentCategory equipmentCategory) {
        Sort sort = new Sort(sortDirection, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return positionService.findByEquipmentCategory(pageRequest, equipmentCategory);
    }

    @GetMapping
    public Page<Position> findByName(@RequestParam("page") int page,
                                     @RequestParam("size") int size,
                                     @RequestParam("name") String name) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return positionService.findByName(pageRequest, name);
    }
}
