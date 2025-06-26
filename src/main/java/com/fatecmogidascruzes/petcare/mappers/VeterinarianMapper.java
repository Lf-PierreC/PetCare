package com.fatecmogidascruzes.petcare.mappers;

import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianResponseDTO;
import com.fatecmogidascruzes.petcare.models.User;
import com.fatecmogidascruzes.petcare.models.Veterinarian;

public class VeterinarianMapper {
    public static Veterinarian toEntity (VeterinarianRequestDTO dto)
    {
        User user = new User();
        Veterinarian veterinarian = new Veterinarian();

        user.setName(dto.getName());
        user.setPhone(dto.getPhone());

        veterinarian.setCrmv(dto.getCrmv());
        veterinarian.setUser(user);

        return veterinarian;
    }

    public static VeterinarianResponseDTO toDTO (Veterinarian veterinarian)
    {
        VeterinarianResponseDTO dto = new VeterinarianResponseDTO();

        dto.setId(veterinarian.getId());
        dto.setName(veterinarian.getUser().getName());
        dto.setPhone(veterinarian.getUser().getPhone());
        dto.setCrmv(veterinarian.getCrmv());
        dto.setCreatedAt(veterinarian.getCreatedAt());

        return dto;
    }
}
