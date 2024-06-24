package com.tourism.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "employee")
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployee;

    @Column(length = 50, nullable = false)
    private String name;//------KEYCLOAK--------

    @Column(length = 50, nullable = false)
    private String lastName;//------KEYCLOAK--------

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 20, nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    @Column(length = 20, nullable = false)
    private String nationality;

    @Column(length = 20, nullable = false)
    private String cellPhone;

    @Column(length = 50, nullable = false)
    @Email
    private String email;//------KEYCLOAK--------

    @Column(length = 50, nullable = false)
    private String position;

    @Column(nullable = false)
    private Double salary;

    //relationships between tables
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleEntity> sales;
}
