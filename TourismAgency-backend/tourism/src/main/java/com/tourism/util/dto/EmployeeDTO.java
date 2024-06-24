package com.tourism.util.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer idEmployee;
    private String name;
    private String lastName;
    private String address;
    private String dni;
    private LocalDate birthdate;
    private String nationality;
    private String cellPhone;
    private String email;
    private String position;
    private Double salary;
}
