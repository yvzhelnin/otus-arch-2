package ru.yvzhelnin.otus.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.warehouse.dto.model.AddModelDto;
import ru.yvzhelnin.otus.warehouse.dto.model.GetModelDto;
import ru.yvzhelnin.otus.warehouse.service.ModelService;

import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public String addModel(@RequestBody AddModelDto addModelDto) {
        return modelService.addModel(addModelDto);
    }

    @GetMapping
    public List<GetModelDto> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping("/{brandCode}")
    public List<GetModelDto> getBrandModels(@PathVariable Long brandCode) {
        return modelService.getBrandModels(brandCode);
    }
}
