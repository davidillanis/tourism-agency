package com.tourism.model.repository;

import com.tourism.model.entity.ServiceTourismEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTourismRepository extends JpaRepository<ServiceTourismEntity, String> {
}
