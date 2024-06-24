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

import com.tourism.model.entity.SaleEntity;
import com.tourism.service.mapped.CustomerService;
import com.tourism.service.mapped.EmployeeService;
import com.tourism.service.mapped.PackageTourismService;
import com.tourism.service.mapped.SaleService;
import com.tourism.service.mapped.ServiceTourismService;
import com.tourism.util.dto.SaleDTO;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ServiceTourismService serviceTourismService;

    @Autowired
    private PackageTourismService packageTourismService;

    @PostMapping("/create")
    //@PreAuthorize("hasRole('CUSTOMER')")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'EMPLOYEE')")
    public ResponseEntity<String> createEntity(@RequestBody SaleDTO sale){
        SaleEntity saleEntity=SaleEntity.builder()
            .numberSale(sale.getNumberSale())
            .dateSale(sale.getDateSale())
            .paymentMethod(sale.getPaymentMethod())
            .customer(customerService.getEntity(sale.getIdCustomer()))
            .employee(employeeService.getEntity(sale.getIdEmployee()))
            .packages(sale.getCodePackages().stream().map(p->packageTourismService.getEntity(p)).collect(Collectors.toList()))
            .services(sale.getCodeServices().stream().map(s->serviceTourismService.getEntity(s)).collect(Collectors.toList()))
        .build();
        saleService.addEntity(saleEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE')")
    public ResponseEntity<List<SaleDTO>> getListEntity(){
        List<SaleDTO> saleDTOList=saleService.getListEntity().stream().map(t->
            SaleDTO.builder()
                .numberSale(t.getNumberSale())
                .dateSale(t.getDateSale())
                .paymentMethod(t.getPaymentMethod())
                .idCustomer(t.getCustomer().getIdCustomer())//only the id is entered
                .idEmployee(t.getEmployee().getIdEmployee())//only the id is entered
                .codePackages(t.getPackages().stream().map(p->new String(p.getCodePackage())).collect(Collectors.toList()))
                .codeServices(t.getServices().stream().map(s->new String(s.getCodeService())).collect(Collectors.toList()))
            .build()
        ).collect(Collectors.toList());
        return new ResponseEntity<>(saleDTOList, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<SaleDTO> getEntity(@PathVariable("id") Integer id) {
        SaleEntity sale = saleService.getEntity(id);
        SaleDTO saleDTO = SaleDTO.builder()
                .numberSale(sale.getNumberSale())
                .dateSale(sale.getDateSale())
                .paymentMethod(sale.getPaymentMethod())
                .idCustomer(sale.getCustomer().getIdCustomer())
                .idEmployee(sale.getEmployee().getIdEmployee())
                .codePackages(sale.getPackages().stream().map(p -> new String(p.getCodePackage())).collect(Collectors.toList()))
                .codeServices(sale.getServices().stream().map(s -> new String(s.getCodeService())).collect(Collectors.toList()))
                .build();
        return new ResponseEntity<>(saleDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> updateEntity(@RequestBody SaleDTO sale){
        SaleEntity saleEntity=SaleEntity.builder()
            .numberSale(sale.getNumberSale())
            .dateSale(sale.getDateSale())
            .paymentMethod(sale.getPaymentMethod())
            .customer(customerService.getEntity(sale.getIdCustomer()))
            .employee(employeeService.getEntity(sale.getIdEmployee()))
            .packages(sale.getCodePackages().stream().map(p->packageTourismService.getEntity(p)).collect(Collectors.toList()))
            .services(sale.getCodeServices().stream().map(s->serviceTourismService.getEntity(s)).collect(Collectors.toList()))
        .build();
        saleService.editEntity(saleEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> deleteMapping(@PathVariable("id") Integer id){
        SaleEntity sale=saleService.getEntity(id);
        sale.getServices().stream().forEach(t->{
            serviceTourismService.deleteEntity(t.getCodeService());
        });
        sale.getPackages().stream().forEach(t->{
            packageTourismService.deleteEntity(t.getCodePackage());
        });
        customerService.deleteEntity(sale.getCustomer().getIdCustomer());
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
