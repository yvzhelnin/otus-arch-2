package ru.yvzhelnin.otus.warehouse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;
}
