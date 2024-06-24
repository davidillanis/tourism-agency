package com.tourism.service.mapped.implementation;

import com.tourism.model.entity.CustomerEntity;
import com.tourism.model.repository.CustomerRepository;
import com.tourism.service.mapped.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean addEntity(CustomerEntity c) {
        if(c!=null) {
            customerRepository.save(c);
            return true;
        }
        return false;
    }

    @Override
    public List<CustomerEntity> getListEntity() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity getEntity(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public boolean editEntity(CustomerEntity c) {
        CustomerEntity c_db=customerRepository.findById(c.getIdCustomer()).orElse(null);
        if(c_db!=null){
            c_db.setAddress(c.getAddress());
            c_db.setBirthdate(c.getBirthdate());
            c_db.setDni(c.getDni());
            c_db.setEmail(c.getEmail());
            c_db.setName(c.getName());
            c_db.setAddress(c.getAddress());
            c_db.setUsernameKeyCloak(c.getUsernameKeyCloak());
            //c_db.setSales(c.getSales());
            c_db.setCellPhone(c.getCellPhone());
            c_db.setLastName(c.getLastName());
            customerRepository.save(c_db);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        if(id!=null && customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Integer searchIdByKeyCloakUsername(String username) {
        return customerRepository.findIdByUsernameKeyCloak(username).orElse(null);
    }
}
