package com.tourism.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;

    @Column(length = 50, nullable = false)
    private String name;//------KEYCLOAK--------

    @Column(length = 50)
    private String lastName;//------KEYCLOAK--------

    @Column(length = 50)
    private String address;

    @Column(length = 20)
    private String dni;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    @Column(length = 20)
    private String nationality;

    @Column(length = 20)
    private String cellPhone;

    @Column(length = 50)
    @Email
    private String email;//------KEYCLOAK--------

    @Column(length = 50, unique = true)
    private String usernameKeyCloak;

    //relationships between tables
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleEntity> sales;
}
