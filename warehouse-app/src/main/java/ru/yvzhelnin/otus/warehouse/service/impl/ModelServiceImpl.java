package ru.yvzhelnin.otus.warehouse.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yvzhelnin.otus.warehouse.dto.model.AddModelDto;
import ru.yvzhelnin.otus.warehouse.dto.model.GetModelDto;
import ru.yvzhelnin.otus.warehouse.exception.WarehouseException;
import ru.yvzhelnin.otus.warehouse.model.Brand;
import ru.yvzhelnin.otus.warehouse.model.Model;
import ru.yvzhelnin.otus.warehouse.repository.BrandRepository;
import ru.yvzhelnin.otus.warehouse.repository.ModelRepository;
import ru.yvzhelnin.otus.warehouse.service.ModelService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    private final BrandRepository brandRepository;

    private final ModelRepository modelRepository;

    public ModelServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public String addModel(AddModelDto addModelDto) {
        final Model existingModel = modelRepository.findByName(addModelDto.getName());
        if (existingModel != null) {
            throw new WarehouseException("Model " + addModelDto + " already exists");
        }
        Brand brand = brandRepository.findById(addModelDto.getBrandCode())
                .orElseThrow(() -> new WarehouseException("Brand with id = " + addModelDto.getBrandCode() + " not found"));

        Model model = new Model();
        model.setArticle(UUID.randomUUID().toString());
        model.setBrand(brand);
        model.setName(addModelDto.getName());
        model.setEquipmentCategory(addModelDto.getEquipmentCategory());

        return modelRepository.save(model).getArticle();
    }

    @Override
    public List<GetModelDto> getAllModels() {
        return modelRepository.findAll().stream()
                .map(model -> new GetModelDto(model.getArticle(),
                        model.getBrand().getCode(),
                        model.getBrand().getName(),
                        model.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetModelDto> getBrandModels(Long brandCode) {
        Brand brand = brandRepository.findById(brandCode)
                .orElseThrow(() -> new WarehouseException("Brand with id = " + brandCode + " not found"));

        return modelRepository.findAllByBrand(brand).stream()
                .map(model -> new GetModelDto(model.getArticle(),
                        model.getBrand().getCode(),
                        model.getBrand().getName(),
                        model.getName()))
                .collect(Collectors.toList());
    }
}
