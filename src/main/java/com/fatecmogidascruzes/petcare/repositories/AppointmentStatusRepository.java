package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatecmogidascruzes.petcare.models.AppointmentStatus;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Long> {
    
}
