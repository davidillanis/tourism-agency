package com.tourism.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "service")
@Builder
public class ServiceTourismEntity {

    @Id
    @Column(length = 10, nullable = false)
    private String codeService;

    @Column(length = 50, nullable = false)
    private String nameService;

    @Column(length = 200)
    private String urlImage;

    @Column(length = 100)
    private String description;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate dateService;

    @Column(nullable = false)
    private Double costService;

    @Column(nullable = false)
    private boolean available;

}
