package com.tourism.service.mapped.implementation;

import com.tourism.model.entity.SaleEntity;
import com.tourism.model.repository.SaleRepository;
import com.tourism.service.mapped.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public boolean addEntity(SaleEntity s) {
        if(s!=null) {
            saleRepository.save(s);
            return true;
        }
        return false;
    }

    @Override
    public List<SaleEntity> getListEntity() {
        return saleRepository.findAll();
    }

    @Override
    public SaleEntity getEntity(Integer id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public boolean editEntity(SaleEntity s) {
        SaleEntity s_db=saleRepository.findById(s.getNumberSale()).orElse(null);
        if(s_db!=null){
            s_db.setServices(s.getServices());
            s_db.setDateSale(s.getDateSale());
            s_db.setPackages(s.getPackages());
            s_db.setCustomer(s.getCustomer());
            s_db.setEmployee(s.getEmployee());
            s_db.setPaymentMethod(s.getPaymentMethod());
            saleRepository.save(s_db);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        if(id!=null && saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
