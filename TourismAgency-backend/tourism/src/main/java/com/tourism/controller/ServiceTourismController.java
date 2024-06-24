package com.tourism.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tourism.model.entity.ServiceTourismEntity;
import com.tourism.service.mapped.ServiceTourismService;

@RestController
@RequestMapping("/api/service")
public class ServiceTourismController {
    @Autowired
    private ServiceTourismService serviceTourismService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> createEntity(@RequestBody ServiceTourismEntity serviceEntity){
        serviceTourismService.addEntity(serviceEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list/{available}")
    public ResponseEntity<List<ServiceTourismEntity>> getListEntity(@PathVariable("available") String available) {
        List<ServiceTourismEntity> serviceEntities = serviceTourismService.getListEntity().stream().filter(service->{
            if(available.equals("true")){
                return service.isAvailable();
            } else if (available.equals("false")) {
                return !service.isAvailable();
            }
            return true;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(serviceEntities, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    public ResponseEntity<ServiceTourismEntity> getEntity(@PathVariable("id") String id){
        ServiceTourismEntity serviceEntity=serviceTourismService.getEntity(id);
        return new ResponseEntity<>(serviceEntity, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> updateEntity(@RequestBody ServiceTourismEntity serviceEntity){
        serviceTourismService.editEntity(serviceEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") String id){
        serviceTourismService.deleteEntity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
