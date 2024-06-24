package com.tourism.model.repository;

import com.tourism.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    @Query("SELECT c.idCustomer FROM CustomerEntity c WHERE c.usernameKeyCloak = :username")
    Optional<Integer> findIdByUsernameKeyCloak(String username);
}
