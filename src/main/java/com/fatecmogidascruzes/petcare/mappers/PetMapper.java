package com.fatecmogidascruzes.petcare.mappers;

import com.fatecmogidascruzes.petcare.dtos.Pet.PetRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Pet.PetResponseDTO;
import com.fatecmogidascruzes.petcare.models.Breed;
import com.fatecmogidascruzes.petcare.models.Customer;
import com.fatecmogidascruzes.petcare.models.Pet;
import com.fatecmogidascruzes.petcare.models.Sex;
import com.fatecmogidascruzes.petcare.models.Size;

public class PetMapper {
    public static Pet toEntity (PetRequestDTO petRequestDTO, Breed breed, Sex sex, Size size, Customer customer)
    {
        Pet pet = new Pet();

        pet.setName(petRequestDTO.getName());
        pet.setBirth(petRequestDTO.getBirth());
        pet.setWeight(petRequestDTO.getWeight());
        pet.setColor(petRequestDTO.getColor());
        pet.setBreed(breed);
        pet.setSex(sex);
        pet.setSize(size);
        pet.setCustomer(customer);

        return pet;
    }

    public static PetResponseDTO toDTO (Pet pet)
    {
        PetResponseDTO dto = new PetResponseDTO();

        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setBirth(pet.getBirth());
        dto.setWeight(pet.getWeight());
        dto.setColor(pet.getColor());
        dto.setBreed(pet.getBreed().getName());
        dto.setSex(pet.getSex().getName());
        dto.setSize(pet.getSize().getName());
        dto.setCustomer(pet.getCustomer().getUser().getName());

        return dto;
    }
}
