package com.tourism.model.entity;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "sale")
@Builder
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numberSale;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateSale;

    @Column(length = 30, nullable = false)//this frontend additional option, (it is not known)
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "idCustomer", nullable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "idEmployee", nullable = false)
    private EmployeeEntity employee;

    //relationships between tables
    //relationships entity 'PackageTourismEntity'
    @ManyToMany(fetch = FetchType.EAGER, targetEntity= PackageTourismEntity.class, cascade=CascadeType.ALL)
    @JoinTable(
            name = "sale_package",
            joinColumns = @JoinColumn(name="numberSale", referencedColumnName = "numberSale"),
            inverseJoinColumns = @JoinColumn(name="codePackage", referencedColumnName = "codePackage")
    )
    private List<PackageTourismEntity> packages;

    //relationships entity 'ServiceTourismEntity'
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ServiceTourismEntity.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sale_service",
            joinColumns = @JoinColumn(name = "numberSale", referencedColumnName = "numberSale"),
            inverseJoinColumns = @JoinColumn(name = "codeService", referencedColumnName = "codeService")
    )
    private List<ServiceTourismEntity> services;
}
