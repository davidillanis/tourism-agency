package com.tourism.service.mapped;

import com.tourism.model.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    //create, read, update, delete
    boolean addEntity(EmployeeEntity e);
    List<EmployeeEntity> getListEntity();
    EmployeeEntity getEntity(Integer id);
    boolean editEntity(EmployeeEntity e);
    boolean deleteEntity(Integer id);

    //other method
}
