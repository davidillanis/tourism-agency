package com.tourism.controller;

import com.tourism.model.entity.CustomerEntity;
import com.tourism.service.mapped.CustomerService;
import com.tourism.service.other.KeycloakService;
import com.tourism.util.dto.UserPersonDTO;
import com.tourism.util.dto.UserRoleKeycloakDTO;
import com.tourism.util.other.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/keycloak/user")
//@PreAuthorize("permitAll()")
public class KeycloakController {
    @Autowired
    private KeycloakService keycloakService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(keycloakService.searchUserByUsername(username));
    }

    @GetMapping("/exist-username/{username}")
    public ResponseEntity<Map<String, Boolean>> existUsername(@PathVariable String username){
        Map<String, Boolean> map=new HashMap<>();
        try {
            keycloakService.searchUserByUsername(username).get(0);
            map.put("existUsername", true);
        }catch (Exception e){
            map.put("existUsername", false);
        }
        return ResponseEntity.ok(map);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRoleKeycloakDTO userDTO){
        String response=keycloakService.createUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable String idUser, @RequestBody UserRoleKeycloakDTO userDTO){
        keycloakService.updateUser(idUser, userDTO);
        return ResponseEntity.ok("User update successfully");
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable String idUser){
        keycloakService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/validateToken")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody String token) {
        Map<String, Boolean> map=new HashMap<>();
        try {
            map.put("ValidToken", keycloakService.isValidToken(token));
        }catch (Exception e){
            map.put("ValidToken", false);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /* ---------------------------------- New APIS ---------------------------------- */
    @PostMapping("/create-customer")//permit all
    public ResponseEntity<?> createUserCustomer(@RequestBody UserPersonDTO userDTO){
        UserRoleKeycloakDTO userRoleKeycloakDTO=UserRoleKeycloakDTO.builder()
                .username(userDTO.getUsernameKeyCloak())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .roles(Set.of(ERole.CUSTOMER.name()))
                .build();

        CustomerEntity customerEntity=CustomerEntity.builder()
                .idCustomer(userDTO.getIdCustomer())
                .name(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .address(userDTO.getAddress())
                .dni(userDTO.getDni())
                .birthdate(userDTO.getBirthdate())
                .nationality(userDTO.getNationality())
                .cellPhone(userDTO.getCellPhone())
                .email(userDTO.getEmail())
                .usernameKeyCloak(userDTO.getUsernameKeyCloak())
                .sales(null)
                .build();
        customerService.addEntity(customerEntity);
        keycloakService.createUser(userRoleKeycloakDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/role")//CUSTOMER, EMPLOYEE, OWNER, ADMINISTRATOR
    public ResponseEntity<Map<String, Object>> getRoleByToken(@RequestBody String token) {
        Map<String, Object> map=new HashMap<>();
        try {
            map.put("ROLE", keycloakService.findRoleByToken(token));
            map.put("status","successful");
        }catch (Exception e){
            map.put("ROLE", "");
            map.put("status",e.toString());
        }
        return ResponseEntity.ok(map);
    }
}
