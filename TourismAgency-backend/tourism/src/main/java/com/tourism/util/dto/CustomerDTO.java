package com.tourism.util.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Integer idCustomer;
    private String name;
    private String lastName;
    private String address;
    private String dni;
    private LocalDate birthdate;
    private String nationality;
    private String cellPhone;
    private String email;
    private String usernameKeyCloak;
}
