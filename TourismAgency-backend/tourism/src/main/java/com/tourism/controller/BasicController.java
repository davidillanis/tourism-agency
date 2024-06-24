package com.tourism.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = {"/api"})
public class BasicController {

    @GetMapping("/index")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/roleAdministrator")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String roleAdministrator() {
        return "This is role administrator";
    }

    @GetMapping("/roleOwner")
    @PreAuthorize("hasRole('OWNER')")
    public String roleOwner() {
        return "This is role Owner";
    }

    @GetMapping("/roleEmployee")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String roleEmployee() {
        return "This is role Employee";
    }

    @GetMapping("/roleCustomer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String roleCustomer() {
        return "This is role Customer";
    }
}
