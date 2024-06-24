package com.tourism.service.mapped;

import com.tourism.model.entity.ServiceTourismEntity;

import java.util.List;

public interface ServiceTourismService {
    //create, read, update, delete
    boolean addEntity(ServiceTourismEntity st);
    List<ServiceTourismEntity> getListEntity();
    ServiceTourismEntity getEntity(String id);
    boolean editEntity(ServiceTourismEntity st);
    boolean deleteEntity(String id);

    //other method
}
