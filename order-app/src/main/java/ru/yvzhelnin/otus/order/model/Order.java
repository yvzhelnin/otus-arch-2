package ru.yvzhelnin.otus.order.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "order", name = "order")
public class Order {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_data_id", referencedColumnName = "id")
    private CustomerData customerData;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column
    private int version;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderEquipment> equipment;
}
