package ru.yvzhelnin.otus.warehouse.service;

import ru.yvzhelnin.otus.warehouse.dto.model.AddModelDto;
import ru.yvzhelnin.otus.warehouse.dto.model.GetModelDto;

import java.util.List;

public interface ModelService {

    String addModel(AddModelDto addModelDto);

    List<GetModelDto> getAllModels();

    List<GetModelDto> getBrandModels(Long brandCode);
}
