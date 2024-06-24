package com.tourism;

import com.tourism.configuration.other.JwtUtils;
import com.tourism.service.mapped.CustomerService;
import com.tourism.service.mapped.EmployeeService;
import com.tourism.service.mapped.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tourism.service.other.AddDataDatabase;

@SpringBootApplication
public class TourismApplication implements CommandLineRunner {
	@Autowired
	private AddDataDatabase addDataDatabase;
	@Autowired
	public SaleService saleService;
	@Autowired
	public EmployeeService employeeService;
	@Autowired
	public CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(TourismApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addDataDatabase.addAll();
	}
}
