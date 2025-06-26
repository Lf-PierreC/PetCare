package com.fatecmogidascruzes.petcare.dtos.Veterinarian;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarianResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String crmv;
    private LocalDateTime createdAt;
}
