package com.fatecmogidascruzes.petcare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentResponseDTO;
import com.fatecmogidascruzes.petcare.exceptions.ResourceNotFoundException;
import com.fatecmogidascruzes.petcare.mappers.AppointmentMapper;
import com.fatecmogidascruzes.petcare.models.Appointment;
import com.fatecmogidascruzes.petcare.models.AppointmentStatus;
import com.fatecmogidascruzes.petcare.models.AppointmentServiceType;
import com.fatecmogidascruzes.petcare.models.Pet;
import com.fatecmogidascruzes.petcare.models.Veterinarian;
import com.fatecmogidascruzes.petcare.repositories.AppointmentRepository;
import com.fatecmogidascruzes.petcare.repositories.AppointmentServiceTypeRepository;
import com.fatecmogidascruzes.petcare.repositories.AppointmentStatusRepository;
import com.fatecmogidascruzes.petcare.repositories.PetRepository;
import com.fatecmogidascruzes.petcare.repositories.VeterinarianRepository;
import com.fatecmogidascruzes.petcare.validators.AppointmentValidator;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private AppointmentValidator appointmentValidator;
    private PetRepository petRepository;
    private VeterinarianRepository veterinarianRepository;
    private AppointmentStatusRepository appointmentStatusRepository;
    private AppointmentServiceTypeRepository appointmentServiceTypeRepository;

    public AppointmentService (AppointmentRepository appointmentRepository, AppointmentValidator appointmentValidator, PetRepository petRepository, VeterinarianRepository veterinarianRepository, AppointmentStatusRepository appointmentStatusRepository, AppointmentServiceTypeRepository appointmentServiceTypeRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.appointmentValidator = appointmentValidator;
        this.petRepository = petRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.appointmentServiceTypeRepository = appointmentServiceTypeRepository;
    }

    public List<AppointmentResponseDTO> index ()
    {
        List<Appointment> appointments = this.appointmentRepository.findAll();

        List<AppointmentResponseDTO> output = appointments.stream()
                                                            .map(AppointmentMapper::toDTO)
                                                            .collect(Collectors.toList());

        return output;
    }

    public AppointmentResponseDTO show (Long id)
    {
        Appointment appointment = this.appointmentRepository.findById(id)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        AppointmentResponseDTO output = AppointmentMapper.toDTO(appointment);

        return output;
    }

    public AppointmentResponseDTO store (AppointmentRequestDTO appointmentRequestDTO)
    {
        this.appointmentValidator.validate(appointmentRequestDTO);

        Pet pet = this.petRepository.findById(appointmentRequestDTO.getPetId()).orElseThrow();
        Veterinarian veterinarian = this.veterinarianRepository.findById(appointmentRequestDTO.getVeterinarianId()).orElseThrow();
        AppointmentStatus status = this.appointmentStatusRepository.findById(appointmentRequestDTO.getStatusId()).orElseThrow();
        AppointmentServiceType service = this.appointmentServiceTypeRepository.findById(appointmentRequestDTO.getServiceId()).orElseThrow();

        Appointment appointment = AppointmentMapper.toEntity(appointmentRequestDTO, pet, veterinarian, status, service);

        this.appointmentRepository.save(appointment);

        AppointmentResponseDTO output = AppointmentMapper.toDTO(appointment);

        return output;
    }

    public AppointmentResponseDTO update (Long id, AppointmentRequestDTO appointmentRequestDTO)
    {
        this.appointmentValidator.validate(appointmentRequestDTO);

        Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        Pet pet = this.petRepository.findById(appointmentRequestDTO.getPetId()).orElseThrow();
        Veterinarian veterinarian = this.veterinarianRepository.findById(appointmentRequestDTO.getVeterinarianId()).orElseThrow();
        AppointmentStatus status = this.appointmentStatusRepository.findById(appointmentRequestDTO.getStatusId()).orElseThrow();
        AppointmentServiceType service = this.appointmentServiceTypeRepository.findById(appointmentRequestDTO.getServiceId()).orElseThrow();

        appointment.setDate(appointmentRequestDTO.getDate());
        appointment.setPet(pet);
        appointment.setVeterinarian(veterinarian);
        appointment.setStatus(status);
        appointment.setService(service);

        this.appointmentRepository.save(appointment);

        AppointmentResponseDTO output = AppointmentMapper.toDTO(appointment);

        return output;
    }

    public void delete (Long id)
    {
        Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        this.appointmentRepository.delete(appointment);
    }
}
