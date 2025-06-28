package com.fatecmogidascruzes.petcare.mappers;

import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentResponseDTO;
import com.fatecmogidascruzes.petcare.models.Appointment;
import com.fatecmogidascruzes.petcare.models.AppointmentServiceType;
import com.fatecmogidascruzes.petcare.models.AppointmentStatus;
import com.fatecmogidascruzes.petcare.models.Pet;
import com.fatecmogidascruzes.petcare.models.Veterinarian;

public class AppointmentMapper {
    public static Appointment toEntity (AppointmentRequestDTO appointmentRequestDTO, Pet pet, Veterinarian veterinarian, AppointmentStatus status, AppointmentServiceType service)
    {
        Appointment appointment = new Appointment();

        appointment.setDate(appointmentRequestDTO.getDate());
        appointment.setPet(pet);
        appointment.setVeterinarian(veterinarian);
        appointment.setStatus(status);
        appointment.setService(service);
        
        return appointment;
    }

    public static AppointmentResponseDTO toDTO (Appointment appointment)
    {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setId(appointment.getId());
        dto.setDate(appointment.getDate());
        dto.setPet(appointment.getPet().getName());
        dto.setVeterinarian(appointment.getVeterinarian().getUser().getName());
        dto.setStatus(appointment.getStatus().getName());
        dto.setService(appointment.getService().getName());
        dto.setCreatedAt(appointment.getCreatedAt());

        return dto;
    }
}
