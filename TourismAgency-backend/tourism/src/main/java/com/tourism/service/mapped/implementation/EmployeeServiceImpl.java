package com.tourism.service.mapped.implementation;

import com.tourism.model.entity.EmployeeEntity;
import com.tourism.model.repository.EmployeeRepository;
import com.tourism.service.mapped.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean addEntity(EmployeeEntity e) {
        if(e!=null) {
            employeeRepository.save(e);
            return true;
        }
        return false;
    }

    @Override
    public List<EmployeeEntity> getListEntity() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getEntity(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean editEntity(EmployeeEntity e) {
        EmployeeEntity e_db=employeeRepository.findById(e.getIdEmployee()).orElse(null);
        if(e_db!=null){
            e_db.setAddress(e.getAddress());
            e_db.setBirthdate(e.getBirthdate());
            e_db.setDni(e.getDni());
            e_db.setEmail(e.getEmail());
            e_db.setName(e.getName());
            e_db.setAddress(e.getAddress());
            e_db.setCellPhone(e.getCellPhone());
            e_db.setLastName(e.getLastName());
            e_db.setSalary(e.getSalary());
            e_db.setPosition(e.getPosition());
            employeeRepository.save(e_db);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        if(id!=null && employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
