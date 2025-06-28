package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatecmogidascruzes.petcare.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
