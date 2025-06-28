package com.fatecmogidascruzes.petcare.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Appointment.AppointmentResponseDTO;
import com.fatecmogidascruzes.petcare.responses.ApiResponse;
import com.fatecmogidascruzes.petcare.services.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController (AppointmentService appointmentService)
    {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentResponseDTO>>> index ()
    {
        List<AppointmentResponseDTO> appointments = this.appointmentService.index();

        ApiResponse<List<AppointmentResponseDTO>> output = new ApiResponse<>(200, "Agendamentos encontrados", appointments);

        return ResponseEntity.ok(output);
    }

    @GetMapping("{id}")
    public ApiResponse<AppointmentResponseDTO> show (@PathVariable Long id)
    {
        AppointmentResponseDTO appointment = this.appointmentService.show(id);

        ApiResponse<AppointmentResponseDTO> output = new ApiResponse<>(200, "Agendamento encontrado", appointment);

        return output;
    }

    @PostMapping
    public AppointmentResponseDTO store (@RequestBody AppointmentRequestDTO appointmentRequestDTO)
    {
        AppointmentResponseDTO output = this.appointmentService.store(appointmentRequestDTO);

        return output;
    }

    @PutMapping("{id}")
    public AppointmentResponseDTO update (@PathVariable Long id, @RequestBody AppointmentRequestDTO appointmentRequestDTO)
    {
        AppointmentResponseDTO output = this.appointmentService.update(id, appointmentRequestDTO);

        return output;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id)
    {
        this.appointmentService.delete(id);
    }
}
