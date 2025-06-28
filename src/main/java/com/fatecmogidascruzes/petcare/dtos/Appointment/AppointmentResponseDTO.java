package com.fatecmogidascruzes.petcare.dtos.Appointment;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponseDTO {
    private Long id;
    private LocalDateTime date;
    private String pet;
    private String veterinarian;
    private String status;
    private String service;
    private LocalDateTime createdAt;
}
