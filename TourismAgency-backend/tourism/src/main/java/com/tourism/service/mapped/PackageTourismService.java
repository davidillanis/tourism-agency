package com.tourism.service.mapped;

import com.tourism.model.entity.PackageTourismEntity;

import java.util.List;

public interface PackageTourismService {
    //create, read, update, delete
    boolean addEntity(PackageTourismEntity p);
    List<PackageTourismEntity> getListEntity();
    PackageTourismEntity getEntity(String id);
    boolean editEntity(PackageTourismEntity p);
    boolean deleteEntity(String id);

    //other method
}
