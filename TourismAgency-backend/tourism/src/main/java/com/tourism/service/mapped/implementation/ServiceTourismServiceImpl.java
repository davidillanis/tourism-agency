package com.tourism.service.mapped.implementation;

import com.tourism.model.entity.ServiceTourismEntity;
import com.tourism.model.repository.ServiceTourismRepository;
import com.tourism.service.mapped.ServiceTourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTourismServiceImpl implements ServiceTourismService {
    @Autowired
    private ServiceTourismRepository serviceTourismRepository;
    @Override
    public boolean addEntity(ServiceTourismEntity st) {
        if(st!=null) {
            serviceTourismRepository.save(st);
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceTourismEntity> getListEntity() {
        return serviceTourismRepository.findAll();
    }

    @Override
    public ServiceTourismEntity getEntity(String id) {
        return serviceTourismRepository.findById(id).orElse(null);
    }

    @Override
    public boolean editEntity(ServiceTourismEntity st) {
        ServiceTourismEntity st_db=serviceTourismRepository.findById(st.getCodeService()).orElse(null);
        if(st_db!=null){
            st_db.setCostService(st.getCostService());
            st_db.setUrlImage(st.getUrlImage());
            st_db.setNameService(st.getNameService());
            st_db.setDescription(st.getDescription());
            st_db.setDateService(st.getDateService());
            st_db.setCostService(st.getCostService());
            st_db.setAvailable(st.isAvailable());
            serviceTourismRepository.save(st_db);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity(String id) {
        if(id!=null && serviceTourismRepository.existsById(id)) {
            serviceTourismRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
