package ru.yvzhelnin.otus.warehouse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "warehouse", name = "brand")
public class Brand {

    @Id
    @EqualsAndHashCode.Include
    private Long code;

    @Column(name = "name")
    private String name;
}
