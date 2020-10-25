package ru.yvzhelnin.otus.warehouse.service;

import ru.yvzhelnin.otus.warehouse.dto.brand.AddBrandDto;
import ru.yvzhelnin.otus.warehouse.dto.brand.GetBrandDto;

import java.util.List;

public interface BrandService {

    Long addBrand(AddBrandDto addBrandDto);

    List<GetBrandDto> getAllBrands();
}
