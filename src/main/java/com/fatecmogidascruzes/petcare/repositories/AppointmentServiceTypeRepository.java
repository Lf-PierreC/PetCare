package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatecmogidascruzes.petcare.models.AppointmentServiceType;

public interface AppointmentServiceTypeRepository extends JpaRepository<AppointmentServiceType, Long> {
    
}
