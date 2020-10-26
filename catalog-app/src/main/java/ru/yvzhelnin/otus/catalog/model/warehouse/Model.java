package ru.yvzhelnin.otus.catalog.model.warehouse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.yvzhelnin.otus.catalog.enums.EquipmentCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "warehouse", name = "model")
public class Model {

    @Id
    @EqualsAndHashCode.Include
    private String article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_code", referencedColumnName = "code")
    @EqualsAndHashCode.Include
    private Brand brand;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "book_value")
    private BigDecimal bookValue;

    @Column(name = "equipment_category")
    @Enumerated(EnumType.STRING)
    private EquipmentCategory equipmentCategory;

    @Column(name = "is_loaded_to_catalog")
    private boolean isLoadedToCatalog;
}
