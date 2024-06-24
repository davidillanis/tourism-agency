package com.tourism.util.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PackageTourismDTO {
    private String codePackage;
    private Double cost;
    private String namePackage;
    private List<String> services;
    private boolean available;
}
