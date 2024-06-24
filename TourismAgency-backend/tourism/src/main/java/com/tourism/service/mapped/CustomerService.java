package com.tourism.service.mapped;

import com.tourism.model.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    //create, read, update, delete
    boolean addEntity(CustomerEntity c);
    List<CustomerEntity> getListEntity();
    CustomerEntity getEntity(Integer id);
    boolean editEntity(CustomerEntity c);
    boolean deleteEntity(Integer id);

    //other method
    Integer searchIdByKeyCloakUsername(String username);
}
