package ru.yvzhelnin.otus.warehouse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "warehouse", name = "booking_log")
public class BookingLog {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equip_inventory_number", referencedColumnName = "inventory_number")
    private Equipment equipment;
}
