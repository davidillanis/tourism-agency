package com.tourism.service.mapped.implementation;

import com.tourism.model.entity.PackageTourismEntity;
import com.tourism.model.repository.PackageTourismRepository;
import com.tourism.service.mapped.PackageTourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageTourismServiceImpl implements PackageTourismService {
    @Autowired
    private PackageTourismRepository packageTourismRepository;

    @Override
    public boolean addEntity(PackageTourismEntity p) {
        if(p!=null) {
            packageTourismRepository.save(p);
            return true;
        }
        return false;
    }

    @Override
    public List<PackageTourismEntity> getListEntity() {
        return packageTourismRepository.findAll();
    }

    @Override
    public PackageTourismEntity getEntity(String id) {
        return packageTourismRepository.findById(id).orElse(null);
    }

    @Override
    public boolean editEntity(PackageTourismEntity p) {
        PackageTourismEntity pt_db=packageTourismRepository.findById(p.getCodePackage()).orElse(null);
        if(pt_db!=null){
            pt_db.setServices(p.getServices());
            pt_db.setCost(p.getCost());
            pt_db.setNamePackage(p.getNamePackage());
            pt_db.setAvailable(p.isAvailable());
            packageTourismRepository.save(pt_db);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity(String id) {
        if(id!=null && packageTourismRepository.existsById(id)) {
            packageTourismRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
