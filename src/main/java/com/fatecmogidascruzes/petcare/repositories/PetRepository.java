package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecmogidascruzes.petcare.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    
}
