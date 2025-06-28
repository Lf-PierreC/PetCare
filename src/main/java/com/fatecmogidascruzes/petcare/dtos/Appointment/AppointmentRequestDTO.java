package com.fatecmogidascruzes.petcare.dtos.Appointment;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequestDTO {
    private LocalDateTime date;
    private Long petId;
    private Long veterinarianId;
    private Long statusId;
    private Long serviceId;
}