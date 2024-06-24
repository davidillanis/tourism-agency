package com.tourism.util.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleDTO {
    private Integer numberSale;
    private LocalDate dateSale;
    private String paymentMethod;
    private Integer idCustomer;
    private Integer idEmployee;
    private List<String> codePackages;
    private List<String> codeServices; 
}
