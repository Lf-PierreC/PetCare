package com.fatecmogidascruzes.petcare.dtos.Pet;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequestDTO {
    private String name;
    private LocalDate birth;
    private Long breedId;
    private Long sexId;
    private Long sizeId;
    private Float weight;
    private String color;
    private Long customerId;
}
