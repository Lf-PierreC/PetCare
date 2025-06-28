package com.fatecmogidascruzes.petcare.dtos.Pet;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetResponseDTO {
    private Long id;
    private String name;
    private LocalDate birth;
    private String breed;
    private String sex;
    private String size;
    private Float weight;
    private String color;
    private String customer;
    private LocalDateTime createdAt;
}
