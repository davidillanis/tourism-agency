package com.tourism.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "package")
@Builder
public class PackageTourismEntity {

    @Id
    @Column(length = 10, nullable = false)
    private String codePackage;

    @Column(nullable = false)
    private Double cost;

    @Column(length = 50, nullable = false)
    private String namePackage;

    @Column(nullable = false)
    private boolean available;

    //relationships entity 'ServiceTourismEntity'------ error=>'Multiple representations of the same entity'
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ServiceTourismEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "package_service",
            joinColumns = @JoinColumn(name = "codePackage", referencedColumnName = "codePackage"),
            inverseJoinColumns = @JoinColumn(name = "codeService", referencedColumnName = "codeService")
    )
    private List<ServiceTourismEntity> services;
}
