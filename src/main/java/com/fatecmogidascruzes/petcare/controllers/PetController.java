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

import com.fatecmogidascruzes.petcare.dtos.Pet.PetRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Pet.PetResponseDTO;
import com.fatecmogidascruzes.petcare.responses.ApiResponse;
import com.fatecmogidascruzes.petcare.services.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {
    private PetService petService;

    public PetController (PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PetResponseDTO>>> index ()
    {
        List<PetResponseDTO> pets = this.petService.index();

        ApiResponse<List<PetResponseDTO>> output = new ApiResponse<>(200, "Pets encontrados", pets);

        return ResponseEntity.ok(output);
    }

    @GetMapping("{id}")
    public ApiResponse<PetResponseDTO> show (@PathVariable Long id)
    {
        PetResponseDTO pet = this.petService.show(id);

        ApiResponse<PetResponseDTO> output = new ApiResponse<>(200, "Pet encontrado", pet);

        return output;
    }

    @PostMapping
    public PetResponseDTO store (@RequestBody PetRequestDTO petRequestDTO)
    {
        PetResponseDTO output = this.petService.store(petRequestDTO);

        return output;
    }

    @PutMapping("{id}")
    public PetResponseDTO update (@PathVariable Long id, @RequestBody PetRequestDTO petRequestDTO)
    {
        PetResponseDTO output = this.petService.update(id, petRequestDTO);

        return output;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id)
    {
        this.petService.delete(id);
    }
}
