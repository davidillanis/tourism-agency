package com.tourism.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourism.model.entity.PackageTourismEntity;
import com.tourism.service.mapped.PackageTourismService;
import com.tourism.service.mapped.ServiceTourismService;
import com.tourism.util.dto.PackageTourismDTO;

@RestController
@RequestMapping("/api/package")
public class PackageTourismController {
    @Autowired
    private PackageTourismService packageTourismService;
    @Autowired
    private ServiceTourismService serviceTourismService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> createEntity(@RequestBody PackageTourismDTO p) {
        PackageTourismEntity packageTourism = PackageTourismEntity.builder()
                .codePackage(p.getCodePackage())
                .cost(p.getCost())
                .namePackage(p.getNamePackage())
                .available(p.isAvailable())
                .services(
                        p.getServices().stream().map(t -> serviceTourismService.getEntity(t.toString())).toList()
                ).build();
        packageTourismService.addEntity(packageTourism);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list/{available}")
    public ResponseEntity<List<PackageTourismDTO>> getListEntity(@PathVariable("available") String available) {
        List<PackageTourismDTO> packageTourismDTOList = packageTourismService.getListEntity().stream()
                .filter(t->{
                    if(available.equals("true")){
                        return t.isAvailable();
                    } else if (available.equals("false")) {
                        return !t.isAvailable();
                    }
                    return true;
                })
                .map(t -> PackageTourismDTO.builder()
                        .codePackage(t.getCodePackage())
                        .namePackage(t.getNamePackage())
                        .cost(t.getCost())
                        .available(t.isAvailable())
                        .services(
                                t.getServices().stream().map(s -> new String(s.getCodeService()))
                                        .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(packageTourismDTOList, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    public ResponseEntity<PackageTourismDTO> getEntity(@PathVariable("id") String id) {
        try {
            PackageTourismEntity p = packageTourismService.getEntity(id);
            PackageTourismDTO packageTourismDTO = PackageTourismDTO.builder()
                    .codePackage(p.getCodePackage())
                    .cost(p.getCost())
                    .namePackage(p.getNamePackage())
                    .available(p.isAvailable())
                    .services(p.getServices().stream().map(t -> new String(t.getCodeService())).toList())
                    .build();
            System.out.println(packageTourismDTO);
            return new ResponseEntity<>(packageTourismDTO, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> updateEntity(@RequestBody PackageTourismDTO p) {

        PackageTourismEntity packageTourism = PackageTourismEntity.builder()
                .codePackage(p.getCodePackage())
                .cost(p.getCost())
                .namePackage(p.getNamePackage())
                .available(p.isAvailable())
                .services(p.getServices().stream()
                        .map(serviceTourismService::getEntity)
                        .filter(entity -> entity != null)
                        .collect(Collectors.toList()))
                .build();
        packageTourismService.editEntity(packageTourism);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") String id) {
        List<String> codeServiceList = packageTourismService.getListEntity().stream().map(t -> new String(t.getCodePackage())).collect(Collectors.toList());
        codeServiceList.stream().forEach(t -> {
            serviceTourismService.deleteEntity(t);
        });
        packageTourismService.deleteEntity(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
