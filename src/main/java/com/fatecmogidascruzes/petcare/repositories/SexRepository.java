package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecmogidascruzes.petcare.models.Sex;

@Repository
public interface SexRepository extends JpaRepository<Sex, Long> {
    
}
