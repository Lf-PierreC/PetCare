package com.fatecmogidascruzes.petcare.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianRequestDTO;
import com.fatecmogidascruzes.petcare.exceptions.ValidationException;

@Component
public class VeterinarianValidator {
    public boolean validate (VeterinarianRequestDTO dto)
    {
        Map<String, List<String>> errors = new HashMap<>();

        if (dto.getName().isEmpty()) {
            this.add(errors, "name", "O nome deve ser informado");
        }

        if (dto.getPhone().isEmpty()) {
            this.add(errors, "phone", "O telefone deve ser informado");
        }

        if (dto.getPhone().length() != 11) {
            this.add(errors, "phone", "Telefone inválido");
        }

        if (dto.getCrmv().isEmpty()) {
            this.add(errors, "crmv", "Crmv inválido");
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
