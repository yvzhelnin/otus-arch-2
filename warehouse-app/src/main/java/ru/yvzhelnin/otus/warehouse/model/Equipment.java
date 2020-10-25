package ru.yvzhelnin.otus.warehouse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentStatus;

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
@Table(schema = "warehouse", name = "equipment")
public class Equipment {

    @Id
    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_article", referencedColumnName = "article")
    private Model model;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private EquipmentStatus equipmentStatus;
}
