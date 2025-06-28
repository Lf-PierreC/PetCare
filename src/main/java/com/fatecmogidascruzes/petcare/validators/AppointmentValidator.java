package com.fatecmogidascruzes.petcare.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentRequestDTO;
import com.fatecmogidascruzes.petcare.exceptions.ValidationException;
import com.fatecmogidascruzes.petcare.repositories.AppointmentServiceTypeRepository;
import com.fatecmogidascruzes.petcare.repositories.AppointmentStatusRepository;
import com.fatecmogidascruzes.petcare.repositories.PetRepository;
import com.fatecmogidascruzes.petcare.repositories.VeterinarianRepository;

@Component
public class AppointmentValidator {
    private PetRepository petRepository;
    private VeterinarianRepository veterinarianRepository;
    private AppointmentStatusRepository appointmentStatusRepository;
    private AppointmentServiceTypeRepository appointmentServiceTypeRepository;

    public AppointmentValidator (PetRepository petRepository, VeterinarianRepository veterinarianRepository, AppointmentStatusRepository appointmentStatusRepository, AppointmentServiceTypeRepository appointmentServiceTypeRepository)
    {
        this.petRepository = petRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.appointmentServiceTypeRepository = appointmentServiceTypeRepository;
    }

    public boolean validate (AppointmentRequestDTO dto)
    {
        Map<String, List<String>> errors = new HashMap<>();

        if (dto.getDate() == null) {
            this.add(errors, "date", "A data deve ser informada");
        }

        if (dto.getPetId() == null) {
            this.add(errors, "pet", "O pet deve ser informado");
        }

        if (!this.petRepository.existsById(dto.getPetId())) {
            this.add(errors, "pet", "Pet inválido");
        }

        if (dto.getVeterinarianId() == null) {
            this.add(errors, "veterinarian", "O veterinário deve ser informado");
        }

        if (!this.veterinarianRepository.existsById(dto.getVeterinarianId())) {
            this.add(errors, "veterinarian", "Veterinário inválido");
        }

        if (dto.getStatusId() == null) {
            this.add(errors, "statusId", "O status deve ser informado");
            System.out.println("É Nulo");
        }

        if (!this.appointmentStatusRepository.existsById(dto.getStatusId())) {
            this.add(errors, "statusId", "Status inválido");
        }

        if (dto.getServiceId() == null) {
            this.add(errors, "serviceId", "O serviço deve ser informado");
        }

        if (!this.appointmentServiceTypeRepository.existsById(dto.getServiceId())) {
            this.add(errors, "serviceId", "Serviço inválido");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        return true;
    }

    private void add (Map<String, List<String>> errors, String field, String message)
    {
        errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
    }
}
