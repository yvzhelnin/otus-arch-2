package ru.yvzhelnin.otus.order.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "order", name = "customer_data")
public class CustomerData {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "deliver_from")
    private LocalDateTime deliverFrom;

    @Column(name = "deliver_till")
    private LocalDateTime deliverTill;

    @Column(name = "return_date")
    private LocalDate returnDate;
}
