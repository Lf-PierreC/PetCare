package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatecmogidascruzes.petcare.models.Species;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    
}
