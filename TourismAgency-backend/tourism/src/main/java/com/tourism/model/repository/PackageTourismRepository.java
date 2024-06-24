package com.tourism.model.repository;

import com.tourism.model.entity.PackageTourismEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageTourismRepository extends JpaRepository<PackageTourismEntity, String> {
}
