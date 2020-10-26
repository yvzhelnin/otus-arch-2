package ru.yvzhelnin.otus.catalog.model.catalog;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.yvzhelnin.otus.catalog.enums.EquipmentCategory;
import ru.yvzhelnin.otus.catalog.enums.SeasonType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "catalog", name = "position")
public class Position {

    @Id
    @EqualsAndHashCode.Include
    private String article;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "pledge")
    private BigDecimal pledge;

    @Column(name = "daily_cost")
    private BigDecimal dailyCost;

    @Column(name = "equipment_category")
    @Enumerated(EnumType.STRING)
    private EquipmentCategory equipmentCategory;

    @Column(name = "season")
    @Enumerated(EnumType.STRING)
    private SeasonType seasonType;
}
