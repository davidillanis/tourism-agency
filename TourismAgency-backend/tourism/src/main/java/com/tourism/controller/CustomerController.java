package com.tourism.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tourism.model.entity.CustomerEntity;
import com.tourism.service.mapped.CustomerService;
import com.tourism.util.dto.CustomerDTO;

@RestController
@RequestMapping(path = {"/api/customer"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createEntity(@RequestBody CustomerDTO c){
        CustomerEntity customerEntity=CustomerEntity.builder()
                .idCustomer(c.getIdCustomer())
                .name(c.getName())
                .lastName(c.getLastName())
                .address(c.getAddress())
                .dni(c.getDni())
                .birthdate(c.getBirthdate())
                .nationality(c.getNationality())
                .cellPhone(c.getCellPhone())
                .email(c.getEmail())
                .usernameKeyCloak(c.getUsernameKeyCloak())
                .sales(null)
        .build();
        customerService.addEntity(customerEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<CustomerDTO>> getListEntity(){
        List<CustomerDTO> customerDTOList=customerService.getListEntity().stream()
                .map(t -> CustomerDTO.builder()
                        .idCustomer(t.getIdCustomer())
                        .name(t.getName())
                        .lastName(t.getLastName())
                        .address(t.getAddress())
                        .dni(t.getDni())
                        .birthdate(t.getBirthdate())
                        .nationality(t.getNationality())
                        .cellPhone(t.getCellPhone())
                        .email(t.getEmail())
                        .usernameKeyCloak(t.getUsernameKeyCloak())
                        .build()
                ).toList();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<CustomerDTO> getEntity(@PathVariable("id") Integer id){
        CustomerEntity t=customerService.getEntity(id);
        CustomerDTO customerDTO=CustomerDTO.builder()
                .idCustomer(t.getIdCustomer())
                .name(t.getName())
                .lastName(t.getLastName())
                .address(t.getAddress())
                .dni(t.getDni())
                .birthdate(t.getBirthdate())
                .nationality(t.getNationality())
                .cellPhone(t.getCellPhone())
                .email(t.getEmail())
                .usernameKeyCloak(t.getUsernameKeyCloak())
                .build();

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> updateEntity(@RequestBody CustomerDTO c){
        CustomerEntity customerEntity=CustomerEntity.builder()
                .idCustomer(c.getIdCustomer())
                .name(c.getName())
                .lastName(c.getLastName())
                .address(c.getAddress())
                .dni(c.getDni())
                .birthdate(c.getBirthdate())
                .nationality(c.getNationality())
                .cellPhone(c.getCellPhone())
                .email(c.getEmail())
                .usernameKeyCloak(c.getUsernameKeyCloak())
                .sales(null)
        .build();
        customerService.editEntity(customerEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") Integer id){
        customerService.deleteEntity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //other
    @GetMapping("/search/username/{username}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Object>> searchIdByUsername(@PathVariable("username") String username){
        Map<String, Object> map=new HashMap<>();
        map.put("idCustomer",customerService.searchIdByKeyCloakUsername(username));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
