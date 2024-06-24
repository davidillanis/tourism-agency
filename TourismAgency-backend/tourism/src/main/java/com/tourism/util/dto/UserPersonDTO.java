package com.tourism.util.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserPersonDTO {
    private int idCustomer;
    private String firstName;
    private String lastName;
    private String address;
    private String dni;
    private LocalDate birthdate;
    private String nationality;
    private String cellPhone;
    private String email;
    private String usernameKeyCloak;
    private String password;
}
