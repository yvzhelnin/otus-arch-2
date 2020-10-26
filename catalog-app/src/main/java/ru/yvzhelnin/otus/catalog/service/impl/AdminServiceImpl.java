package ru.yvzhelnin.otus.catalog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.catalog.model.catalog.Position;
import ru.yvzhelnin.otus.catalog.repository.catalog.PositionRepository;
import ru.yvzhelnin.otus.catalog.repository.warehouse.ModelRepository;
import ru.yvzhelnin.otus.catalog.service.AdminService;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${catalog.pledge.fraction}")
    private BigDecimal pledgeFraction;

    @Value("${catalog.daily.price.fraction}")
    private BigDecimal dailyPriceFraction;

    private final ModelRepository modelRepository;

    private final PositionRepository positionRepository;

    public AdminServiceImpl(ModelRepository modelRepository, PositionRepository positionRepository) {
        this.modelRepository = modelRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void updateCatalog() {
        var models = modelRepository.findAllByLoadedToCatalog(false);
        var positions = models.stream()
                .map(model -> {
                    Position position = new Position();
                    position.setArticle(model.getArticle());
                    position.setBrandName(model.getBrand().getName());
                    position.setName(model.getName());
                    position.setPledge(model.getBookValue().multiply(pledgeFraction));
                    position.setDailyCost(model.getBookValue().multiply(dailyPriceFraction));
                    position.setEquipmentCategory(model.getEquipmentCategory());
                    position.setSeasonType(model.getEquipmentCategory().getSeasonType());

                    return position;
                })
                .collect(Collectors.toList());

        positionRepository.saveAll(positions);
    }
}
