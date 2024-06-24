package com.tourism.service.mapped;

import com.tourism.model.entity.SaleEntity;

import java.util.List;

public interface SaleService {
    //create, read, update, delete
    boolean addEntity(SaleEntity s);
    List<SaleEntity> getListEntity();
    SaleEntity getEntity(Integer id);
    boolean editEntity(SaleEntity s);
    boolean deleteEntity(Integer id);

    //other method
}
