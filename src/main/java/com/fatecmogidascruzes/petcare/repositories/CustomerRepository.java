package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecmogidascruzes.petcare.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
