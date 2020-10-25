package ru.yvzhelnin.otus.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.warehouse.dto.brand.AddBrandDto;
import ru.yvzhelnin.otus.warehouse.dto.brand.GetBrandDto;
import ru.yvzhelnin.otus.warehouse.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public Long addBrand(@RequestBody AddBrandDto addBrandDto) {
        return brandService.addBrand(addBrandDto);
    }

    @GetMapping
    public List<GetBrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }
}
