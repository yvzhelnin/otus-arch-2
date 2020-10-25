package ru.yvzhelnin.otus.warehouse.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.warehouse.dto.brand.AddBrandDto;
import ru.yvzhelnin.otus.warehouse.dto.brand.GetBrandDto;
import ru.yvzhelnin.otus.warehouse.exception.WarehouseException;
import ru.yvzhelnin.otus.warehouse.model.Brand;
import ru.yvzhelnin.otus.warehouse.repository.BrandRepository;
import ru.yvzhelnin.otus.warehouse.service.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Long addBrand(AddBrandDto addBrandDto) {
        Brand existingBrand = brandRepository.findByName(addBrandDto.getName());
        if (existingBrand != null) {
            throw new WarehouseException("Brand " + addBrandDto + " already exists");
        }
        Brand brand = new Brand();
        brand.setName(addBrandDto.getName());
        return brandRepository.save(brand).getCode();
    }

    @Override
    public List<GetBrandDto> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brand -> new GetBrandDto(brand.getCode(), brand.getName()))
                .collect(Collectors.toList());
    }
}
