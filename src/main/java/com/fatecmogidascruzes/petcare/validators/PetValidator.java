package com.fatecmogidascruzes.petcare.validators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fatecmogidascruzes.petcare.dtos.Pet.PetRequestDTO;
import com.fatecmogidascruzes.petcare.exceptions.ValidationException;
import com.fatecmogidascruzes.petcare.repositories.SpeciesRepository;
import com.fatecmogidascruzes.petcare.repositories.CustomerRepository;
import com.fatecmogidascruzes.petcare.repositories.SexRepository;
import com.fatecmogidascruzes.petcare.repositories.SizeRepository;

@Component
public class PetValidator {
    private SpeciesRepository speciesRepository;
    private SexRepository sexRepository;
    private SizeRepository sizeRepository;
    private CustomerRepository customerRepository;

    public PetValidator (SpeciesRepository speciesRepository, SexRepository sexRepository, SizeRepository sizeRepository, CustomerRepository customerRepository)
    {
        this.speciesRepository = speciesRepository;
        this.sexRepository = sexRepository;
        this.sizeRepository = sizeRepository;
        this.customerRepository = customerRepository;
    }

    public boolean validate (PetRequestDTO dto)
    {
        Map<String, List<String>> errors = new HashMap<>();
        LocalDate today = LocalDate.now();

        if (dto.getName().isEmpty()) {
            this.add(errors, "name", "O nome deve ser informado");
        }

        if (dto.getBirth() == null) {
            this.add(errors, "birth", "O nascimento deve ser informado");
        }

        if (dto.getBirth().isAfter(today)) {
            this.add(errors, "birth", "O nascimento não pode ser no futuro");
        }

        if (dto.getBreed().isEmpty()) {
            this.add(errors, "breed", "A raça deve ser informada");
        }

        if (dto.getSpeciesId() == null) {
            this.add(errors, "species", "A espécie deve ser informada");
        }

        if (!this.speciesRepository.existsById(dto.getSpeciesId())) {
            this.add(errors, "species", "Espécie inválida");
        }

        if (dto.getSexId() == null) {
            this.add(errors, "sex", "O sexo deve ser informado" );
        }

        if (!this.sexRepository.existsById(dto.getSexId())) {
            this.add(errors, "sex", "Sexo inválido");
        }

        if (dto.getSizeId() == null) {
            this.add(errors, "size", "O porte deve ser informado");
        }

        if (!this.sizeRepository.existsById(dto.getSizeId())) {
            this.add(errors, "size", "Porte inválido");
        }

        if (dto.getWeight() <= 1) {
            this.add(errors, "weight", "Peso inválido");
        }

        if (dto.getColor().isEmpty()) {
            this.add(errors, "color", "A cor deve ser informada");
        }

        if (dto.getCustomerId() == null) {
            this.add(errors, "customer", "O cliente deve ser informado");
        }

        if (!this.customerRepository.existsById(dto.getCustomerId())) {
            this.add(errors, "customer", "Cliente inválido");
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
