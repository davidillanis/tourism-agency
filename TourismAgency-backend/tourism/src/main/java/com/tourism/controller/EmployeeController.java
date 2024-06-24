package com.tourism.controller;

import com.tourism.model.entity.EmployeeEntity;
import com.tourism.service.mapped.EmployeeService;
import com.tourism.util.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> createEntity(@RequestBody EmployeeDTO e){
        EmployeeEntity employeeEntity=EmployeeEntity.builder()
                .idEmployee(e.getIdEmployee())
                .name(e.getName())
                .lastName(e.getLastName())
                .address(e.getAddress())
                .dni(e.getDni())
                .birthdate(e.getBirthdate())
                .nationality(e.getNationality())
                .cellPhone(e.getCellPhone())
                .email(e.getEmail())
                .position(e.getPosition())
                .salary(e.getSalary())
                .sales(null)
        .build();
        employeeService.addEntity(employeeEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<List<EmployeeDTO>> getListEntity(){
        List<EmployeeDTO> employeeDTOList=employeeService.getListEntity().stream().map(
                e->EmployeeDTO.builder()
                        .idEmployee(e.getIdEmployee())
                        .name(e.getName())
                        .lastName(e.getLastName())
                        .address(e.getAddress())
                        .dni(e.getDni())
                        .birthdate(e.getBirthdate())
                        .nationality(e.getNationality())
                        .cellPhone(e.getCellPhone())
                        .email(e.getEmail())
                        .position(e.getPosition())
                        .salary(e.getSalary())
                    .build()
        ).toList();
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<EmployeeDTO> getEntity(@PathVariable("id") Integer id){
        try {
            EmployeeEntity e=employeeService.getEntity(id);
            EmployeeDTO employeeDTO=EmployeeDTO.builder()
                    .idEmployee(e.getIdEmployee())
                    .name(e.getName())
                    .lastName(e.getLastName())
                    .address(e.getAddress())
                    .dni(e.getDni())
                    .birthdate(e.getBirthdate())
                    .nationality(e.getNationality())
                    .cellPhone(e.getCellPhone())
                    .email(e.getEmail())
                    .position(e.getPosition())
                    .salary(e.getSalary())
                    .build();
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> updateEntity(@RequestBody EmployeeDTO e){
        EmployeeEntity employeeEntity=EmployeeEntity.builder()
                .idEmployee(e.getIdEmployee())
                .name(e.getName())
                .lastName(e.getLastName())
                .address(e.getAddress())
                .dni(e.getDni())
                .birthdate(e.getBirthdate())
                .nationality(e.getNationality())
                .cellPhone(e.getCellPhone())
                .email(e.getEmail())
                .position(e.getPosition())
                .salary(e.getSalary())
                .sales(null)
            .build();
        employeeService.editEntity(employeeEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") Integer id){
        try {
            employeeService.deleteEntity(id);
        }catch (Exception e){//delete if not reference table sale
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
