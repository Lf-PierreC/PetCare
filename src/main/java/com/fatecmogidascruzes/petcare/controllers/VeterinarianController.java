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

import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianResponseDTO;
import com.fatecmogidascruzes.petcare.responses.ApiResponse;
import com.fatecmogidascruzes.petcare.services.VeterinarianService;

@RestController
@RequestMapping("/veterinarians")
public class VeterinarianController
{
    private VeterinarianService veterinarianService;

    public VeterinarianController (VeterinarianService veterinarianService)
    {
        this.veterinarianService = veterinarianService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VeterinarianResponseDTO>>> index ()
    {
        List<VeterinarianResponseDTO> veterinarians = this.veterinarianService.index();
        ApiResponse<List<VeterinarianResponseDTO>> output = new ApiResponse<>(200, "Veterinários encontrados", veterinarians);

        return ResponseEntity.ok(output);
    }

    @GetMapping("{id}")
    public ApiResponse<VeterinarianResponseDTO> show (@PathVariable Long id)
    {
        VeterinarianResponseDTO veterinarian = this.veterinarianService.show(id);
        ApiResponse<VeterinarianResponseDTO> output = new ApiResponse<>(200, "Cliente encontrado", veterinarian);

        return output;
    }

    @PostMapping
    public VeterinarianResponseDTO store (@RequestBody VeterinarianRequestDTO veterinarianRequestDTO)
    {
        VeterinarianResponseDTO output = this.veterinarianService.store(veterinarianRequestDTO);

        return output;
    }

    @PutMapping("{id}")
    public VeterinarianResponseDTO update (@PathVariable Long id, @RequestBody VeterinarianRequestDTO veterinarianRequestDTO)
    {
        VeterinarianResponseDTO output = this.veterinarianService.update(id, veterinarianRequestDTO);

        return output;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id)
    {
        this.veterinarianService.delete(id);
    }
}
