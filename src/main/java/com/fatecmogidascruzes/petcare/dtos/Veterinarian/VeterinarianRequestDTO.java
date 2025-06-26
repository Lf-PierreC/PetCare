package com.fatecmogidascruzes.petcare.dtos.Veterinarian;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarianRequestDTO {
    private String name;
    private String phone;
    private String crmv;
}
