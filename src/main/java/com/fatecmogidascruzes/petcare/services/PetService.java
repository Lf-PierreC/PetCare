package com.fatecmogidascruzes.petcare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fatecmogidascruzes.petcare.dtos.Pet.PetRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Pet.PetResponseDTO;
import com.fatecmogidascruzes.petcare.exceptions.ResourceNotFoundException;
import com.fatecmogidascruzes.petcare.mappers.PetMapper;
import com.fatecmogidascruzes.petcare.models.Species;
import com.fatecmogidascruzes.petcare.models.Customer;
import com.fatecmogidascruzes.petcare.models.Pet;
import com.fatecmogidascruzes.petcare.models.Sex;
import com.fatecmogidascruzes.petcare.models.Size;
import com.fatecmogidascruzes.petcare.repositories.SpeciesRepository;
import com.fatecmogidascruzes.petcare.repositories.CustomerRepository;
import com.fatecmogidascruzes.petcare.repositories.PetRepository;
import com.fatecmogidascruzes.petcare.repositories.SexRepository;
import com.fatecmogidascruzes.petcare.repositories.SizeRepository;
import com.fatecmogidascruzes.petcare.validators.PetValidator;

@Service
public class PetService {
    private PetRepository petRepository;
    private PetValidator petValidator;
    private SpeciesRepository speciesRepository;
    private SexRepository sexRepository;
    private SizeRepository sizeRepository;
    private CustomerRepository customerRepository;

    public PetService (PetRepository petRepository, PetValidator petValidator, SpeciesRepository speciesRepository, SexRepository sexRepository, SizeRepository sizeRepository, CustomerRepository customerRepository)
    {
        this.petRepository = petRepository;
        this.petValidator = petValidator;
        this.speciesRepository = speciesRepository;
        this.sexRepository = sexRepository;
        this.sizeRepository = sizeRepository;
        this.customerRepository = customerRepository;
    }

    public List<PetResponseDTO> index ()
    {
        List<Pet> pets = this.petRepository.findAll();

        List<PetResponseDTO> output = pets.stream()
                                            .map(PetMapper::toDTO)
                                            .collect(Collectors.toList());

        return output;
    }

    public PetResponseDTO show (Long id)
    {
        Pet pet = this.petRepository.findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        PetResponseDTO output = PetMapper.toDTO(pet);

        return output;
    }

    public PetResponseDTO store (PetRequestDTO petRequestDTO)
    {
        this.petValidator.validate(petRequestDTO);

        Species species = this.speciesRepository.findById(petRequestDTO.getSpeciesId()).orElseThrow();
        Sex sex = this.sexRepository.findById(petRequestDTO.getSexId()).orElseThrow();
        Size size = this.sizeRepository.findById(petRequestDTO.getSizeId()).orElseThrow();
        Customer customer = this.customerRepository.findById(petRequestDTO.getCustomerId()).orElseThrow();

        Pet pet = PetMapper.toEntity(petRequestDTO, species, sex, size, customer);

        this.petRepository.save(pet);

        PetResponseDTO output = PetMapper.toDTO(pet);

        return output;
    }

    public PetResponseDTO update (Long id, PetRequestDTO petRequestDTO)
    {
        this.petValidator.validate(petRequestDTO);

        Pet pet = this.petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Species species = this.speciesRepository.findById(petRequestDTO.getSpeciesId()).orElseThrow();
        Sex sex = this.sexRepository.findById(petRequestDTO.getSexId()).orElseThrow();
        Size size = this.sizeRepository.findById(petRequestDTO.getSizeId()).orElseThrow();
        Customer customer = this.customerRepository.findById(petRequestDTO.getCustomerId()).orElseThrow();

        pet.setName(petRequestDTO.getName());
        pet.setBirth(petRequestDTO.getBirth());
        pet.setWeight(petRequestDTO.getWeight());
        pet.setColor(petRequestDTO.getColor());
        pet.setBreed(petRequestDTO.getBreed());
        pet.setSpecies(species);
        pet.setSex(sex);
        pet.setSize(size);
        pet.setCustomer(customer);

        this.petRepository.save(pet);

        PetResponseDTO output = PetMapper.toDTO(pet);

        return output;
    }

    public void delete (Long id)
    {
        Pet pet = this.petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        this.petRepository.delete(pet);
    }
}
