package ru.yvzhelnin.otus.returnal.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "delivery", name = "customer_data")
public class CustomerData {

    @Id
    private String id;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @EqualsAndHashCode.Include
    @Column(name = "phone")
    private String phone;
}
