package ru.yvzhelnin.otus.warehouse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.yvzhelnin.otus.warehouse.enums.SeasonType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @Column(name = "season_type")
    @Enumerated(EnumType.STRING)
    private SeasonType seasonType;

    @Column(name = "is_loaded_to_catalog")
    private boolean isLoadedToCatalog;
}
