package com.tourism.service.other;

import com.tourism.model.entity.CustomerEntity;
import com.tourism.model.entity.EmployeeEntity;
import com.tourism.model.entity.PackageTourismEntity;
import com.tourism.model.entity.SaleEntity;
import com.tourism.model.entity.ServiceTourismEntity;
import com.tourism.service.mapped.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddDataDatabase {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PackageTourismService packageTourismService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ServiceTourismService serviceTourismService;

    public void addAll(){
        addService();
        addPackage();
        addEmployee();
        addCustomer();
        addSale();
    }

    private final void addCustomer(){
        List<CustomerEntity> customerEntityList= List.of(
                new CustomerEntity(0, "David", "Quispe", "Av. Lampa", "43213213", LocalDate.of(1990, 5, 10), "Chile", "932122312", "david@gmail.com", "all", null),
                new CustomerEntity(0, "Maria", "Gomez", "Calle Principal", "654321", LocalDate.of(1985, 8, 15), "Argentina", "987654321", "maria@example.com", "customer1",null),
                new CustomerEntity(0, "John", "Doe", "Broadway Street", "78901234", LocalDate.of(1982, 3, 20), "USA", "123456789", "john.doe@example.com", null,null),
                new CustomerEntity(0, "Alice", "Johnson", "Maple Avenue", "56789012", LocalDate.of(1995, 11, 5), "Canada", "9876543210", "alice.j@example.com", null, null),
                new CustomerEntity(0, "Carlos", "Sanchez", "Avenida del Sol", "54321098", LocalDate.of(1988, 6, 12), "Mexico", "876543210", "carlos.s@example.com", null, null),
                new CustomerEntity(0, "Sophie", "Martin", "Rue de la Paix", "11223344", LocalDate.of(1993, 9, 25), "France", "1122334455", "sophie.m@example.com", null, null),
                new CustomerEntity(0, "Hiroshi", "Tanaka", "Tokyo Street", "66778899", LocalDate.of(1980, 2, 8), "Japan", "9988776655", "hiroshi.t@example.com", null, null),
                new CustomerEntity(0, "Elena", "Rodriguez", "Calle de la Luna", "44556677", LocalDate.of(1998, 7, 17), "Spain", "6655443322", "elena.r@example.com", null, null),
                new CustomerEntity(0, "Alex", "Wong", "Hong Kong Avenue", "11223344", LocalDate.of(1987, 4, 3), "Hong Kong", "7788990011", "alex.w@example.com", null, null),
                new CustomerEntity(0, "Isabella", "Silva", "Copacabana Beach", "22334455", LocalDate.of(1991, 12, 30), "Brazil", "5544332211", "isabella.s@example.com", null, null)
        );
        customerEntityList.forEach(t->customerService.addEntity(t));
    }
    private final void addEmployee(){
        List<EmployeeEntity> employeeEntityList=List.of(
                new EmployeeEntity(0, "any", "any", "any", "00000000", LocalDate.of(2024, Month.JANUARY, 1), "any", "any", "any@gmail.com", "any", 0.0, null),
                new EmployeeEntity(0, "Tomas", "Gomez", "Jr. Ramon", "31123054", LocalDate.of(2020, 5, 12), "Peru", "943856123", "tomas@gmail.com", "Gerente", 2000.0, null),
                new EmployeeEntity(0, "Laura", "Fernandez", "Av. Principal", "45678901", LocalDate.of(2019, 8, 20), "Argentina", "987654321", "laura@gmail.com", "Analista", 1500.0, null),
                new EmployeeEntity(0, "Carlos", "Rodriguez", "Calle Central", "78901234", LocalDate.of(2021, 3, 5), "Mexico", "123456789", "carlos@gmail.com", "Desarrollador", 1800.0, null),
                new EmployeeEntity(0, "Ana", "Martinez", "Plaza Mayor", "56789012", LocalDate.of(2018, 12, 10), "Spain", "678901234", "ana@gmail.com", "Consultor", 1700.0, null),
                new EmployeeEntity(0, "Pablo", "Lopez", "Rue de la Paix", "12340123", LocalDate.of(2022, 2, 15), "France", "012345678", "pablo@gmail.com", "Especialista", 2200.0, null),
                new EmployeeEntity(0, "Elena", "Sanchez", "Broadway St.", "89112345", LocalDate.of(2020, 6, 25), "USA", "345678901", "elena@gmail.com", "Coordinador", 1900.0, null),
                new EmployeeEntity(0, "Juan", "Ramos", "Kurfürstendamm", "23456789", LocalDate.of(2019, 11, 30), "Germany", "567890123", "juan@gmail.com", "Ingeniero", 2000.0, null),
                new EmployeeEntity(0, "Marta", "Fuentes", "Sydney Opera House", "67890123", LocalDate.of(2021, 7, 8), "Australia", "789012345", "marta@gmail.com", "Arquitecto", 2100.0, null),
                new EmployeeEntity(0, "Raj", "Patel", "Shibuya Crossing", "89012345", LocalDate.of(2018, 9, 3), "Japan", "901234567", "raj@gmail.com", "Analista de Datos", 1800.0, null),
                new EmployeeEntity(0, "Luisa", "Gonzalez", "Carrer de Mallorca", "12345678", LocalDate.of(2022, 1, 12), "Spain", "234567890", "luisa@gmail.com", "Diseñador", 1600.0, null)
        );
        employeeEntityList.forEach(t->employeeService.addEntity(t));
    }
    private final void addService(){
        List<ServiceTourismEntity> serviceTourismEntityList=List.of(
                new ServiceTourismEntity("S001", "Hotel por noche", null, null, LocalDate.of(2021, Month.AUGUST, 21), 200.0, true),
                new ServiceTourismEntity("S002", "Alquiler de auto", null, null, LocalDate.of(2022, Month.JULY, 11), 250.0, true),
                new ServiceTourismEntity("S003", "Pasajes de colectivo", null, null, LocalDate.of(2023, Month.JUNE, 2), 300.0, true),
                new ServiceTourismEntity("S004", "Pasajes de avion", null, null, LocalDate.of(2022, Month.FEBRUARY, 3), 270.0, true),
                new ServiceTourismEntity("S005", "Pasajes de tren", null, null, LocalDate.of(2024, Month.FEBRUARY, 3), 150.0, true),
                new ServiceTourismEntity("S006", "Excurciones", null, null, LocalDate.of(2020, Month.JANUARY, 13), 190.0, true),
                new ServiceTourismEntity("S007", "Desayuno al cuarto", null, null, LocalDate.of(2022, Month.JULY, 1), 20.0, true),
                new ServiceTourismEntity("S008", "Almuerzo", null, null, LocalDate.of(2023, Month.APRIL, 11), 30.0, true),
                new ServiceTourismEntity("S009", "Cena elegante", null, null, LocalDate.of(2024, Month.AUGUST, 23), 10.0, true),
                new ServiceTourismEntity("S010", "Tour Holtel", null, null, LocalDate.of(2024, Month.AUGUST, 21), 300.0, true),
                new ServiceTourismEntity("S011", "Alquiler de elicoptero", null, null, LocalDate.of(2024, Month.AUGUST, 21), 300.0, true),
                new ServiceTourismEntity("S012", "Envio de personal", null, null, LocalDate.of(2024, Month.AUGUST, 21), 300.0, true),
                new ServiceTourismEntity("S013", "Entrada al cine", null, null, LocalDate.of(2024, Month.AUGUST, 21), 30.0, false)

        );
        serviceTourismEntityList.forEach(t->serviceTourismService.addEntity(t));
    }
    private final void addPackage(){
        List<ServiceTourismEntity> serviceTourismPremium1=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S004"),
                serviceTourismService.getEntity("S005")
        );
        List<ServiceTourismEntity> serviceTourismPremium2=List.of(
                serviceTourismService.getEntity("S002"),
                serviceTourismService.getEntity("S003")
        );
        List<ServiceTourismEntity> serviceTourismPremium3=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S002")
        );
        List<ServiceTourismEntity> serviceTourismPremium4=List.of(
                serviceTourismService.getEntity("S013"),
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S003"),
                serviceTourismService.getEntity("S009")
        );
        List<ServiceTourismEntity> serviceTourismPremium5=List.of(
                serviceTourismService.getEntity("S010"),
                serviceTourismService.getEntity("S009"),
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S012"),
                serviceTourismService.getEntity("S003"),
                serviceTourismService.getEntity("S005")
        );
        List<ServiceTourismEntity> serviceTourismPremium6=List.of(
                serviceTourismService.getEntity("S011"),
                serviceTourismService.getEntity("S012"),
                serviceTourismService.getEntity("S013"),
                serviceTourismService.getEntity("S005")
        );
        List<ServiceTourismEntity> serviceTourismPremium7=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S012")
        );
        List<ServiceTourismEntity> serviceTourismPremium8=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S002"),
                serviceTourismService.getEntity("S003"),
                serviceTourismService.getEntity("S005"),
                serviceTourismService.getEntity("S006"),
                serviceTourismService.getEntity("S007"),
                serviceTourismService.getEntity("S008")
        );
        List<ServiceTourismEntity> serviceTourismPremium9=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S009"),
                serviceTourismService.getEntity("S010"),
                serviceTourismService.getEntity("S005"),
                serviceTourismService.getEntity("S011")
        );
        List<ServiceTourismEntity> serviceTourismPremium10=List.of(
                serviceTourismService.getEntity("S011"),
                serviceTourismService.getEntity("S012"),
                serviceTourismService.getEntity("S013")
        );
        List<ServiceTourismEntity> serviceTourismPremium11=List.of(
                serviceTourismService.getEntity("S009"),
                serviceTourismService.getEntity("S010"),
                serviceTourismService.getEntity("S011"),
                serviceTourismService.getEntity("S012")
        );
        List<ServiceTourismEntity> serviceTourismPremium12=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S003"),
                serviceTourismService.getEntity("S005"),
                serviceTourismService.getEntity("S007"),
                serviceTourismService.getEntity("S009"),
                serviceTourismService.getEntity("S011")
        );

        List<PackageTourismEntity> packageTourismEntitieList=List.of(
                new PackageTourismEntity("P001", 900.0, "premium1", true, serviceTourismPremium1),
                new PackageTourismEntity("P002", 1000.0, "premium2", true, serviceTourismPremium2),
                new PackageTourismEntity("P003", 350.0, "premium3", true, serviceTourismPremium3),
                new PackageTourismEntity("P004", 190.0, "premium4", true, serviceTourismPremium4),
                new PackageTourismEntity("P005", 380.0, "premium5", true, serviceTourismPremium5),
                new PackageTourismEntity("P006", 530.0, "premium6", true, serviceTourismPremium6),
                new PackageTourismEntity("P007", 120.0, "premium7", false, serviceTourismPremium7),
                new PackageTourismEntity("P008", 200.0, "premium8", false, serviceTourismPremium8),
                new PackageTourismEntity("P009", 520.0, "premium9", false, serviceTourismPremium9),
                new PackageTourismEntity("P010", 160.0, "premium10", false, serviceTourismPremium10),
                new PackageTourismEntity("P011", 400.0, "premium11", true, serviceTourismPremium11),
                new PackageTourismEntity("P012", 200.0, "premium12", true, serviceTourismPremium12),

                new PackageTourismEntity("P999", 3000.0, "premium13", true, serviceTourismService.getListEntity())
        );
        packageTourismEntitieList.forEach(t->packageTourismService.addEntity(t));
    }
    private void addSale(){
        List<ServiceTourismEntity> serviceTourismEntities=List.of(
                serviceTourismService.getEntity("S001"),
                serviceTourismService.getEntity("S002"),
                serviceTourismService.getEntity("S003")
        );
        List<PackageTourismEntity> packageTourismEntities=List.of(
                packageTourismService.getEntity("P001"),
                packageTourismService.getEntity("P002"),
                packageTourismService.getEntity("P003")
        );

        List<SaleEntity> saleEntityList=List.of(
                new SaleEntity(0, LocalDate.of(2023, Month.JANUARY, 2), "Yape", customerService.getEntity(1), employeeService.getEntity(1), packageTourismEntities, null),
                new SaleEntity(0, LocalDate.of(2021, Month.APRIL, 20), "Yape", customerService.getEntity(1), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2022, Month.DECEMBER, 21), "Efective", customerService.getEntity(1), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.NOVEMBER, 11), "Efective", customerService.getEntity(1), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2022, Month.AUGUST, 12), "Efective", customerService.getEntity(1), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2022, Month.FEBRUARY, 20), "Efective", customerService.getEntity(1), employeeService.getEntity(1), packageTourismEntities, serviceTourismEntities),

                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Yape", customerService.getEntity(2), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Yape", customerService.getEntity(2), employeeService.getEntity(1), packageTourismEntities, null),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(2), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(2), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(2), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(2), employeeService.getEntity(1), packageTourismEntities, serviceTourismEntities),

                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Yape", customerService.getEntity(3), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Yape", customerService.getEntity(3), employeeService.getEntity(1), packageTourismEntities, null),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(3), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(3), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(3), employeeService.getEntity(1), null, serviceTourismEntities),
                new SaleEntity(0, LocalDate.of(2023, Month.DECEMBER, 20), "Efective", customerService.getEntity(3), employeeService.getEntity(1), packageTourismEntities, serviceTourismEntities)

        );
        saleEntityList.forEach(t->saleService.addEntity(t));
    }
}
