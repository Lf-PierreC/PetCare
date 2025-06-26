package com.fatecmogidascruzes.petcare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Veterinarian.VeterinarianResponseDTO;
import com.fatecmogidascruzes.petcare.exceptions.ResourceNotFoundException;
import com.fatecmogidascruzes.petcare.mappers.VeterinarianMapper;
import com.fatecmogidascruzes.petcare.models.Veterinarian;
import com.fatecmogidascruzes.petcare.models.User;
import com.fatecmogidascruzes.petcare.repositories.VeterinarianRepository;
import com.fatecmogidascruzes.petcare.repositories.UserRepository;
import com.fatecmogidascruzes.petcare.validators.VeterinarianValidator;

@Service
public class VeterinarianService {
    private VeterinarianRepository veterinarianRepository;
    private UserRepository userRepository;
    private VeterinarianValidator veterinarianValidator;

    public VeterinarianService (VeterinarianRepository veterinarianRepository, UserRepository userRepository, VeterinarianValidator veterinarianValidator)
    {
        this.veterinarianRepository = veterinarianRepository;
        this.userRepository = userRepository;
        this.veterinarianValidator = veterinarianValidator;
    }

    public List<VeterinarianResponseDTO> index ()
    {
        List<Veterinarian> veterinarians = this.veterinarianRepository.findAll();

        List<VeterinarianResponseDTO> output = veterinarians.stream()
                                                    .map(VeterinarianMapper::toDTO)
                                                    .collect(Collectors.toList());

        return output;
    }

    public VeterinarianResponseDTO show (Long id)
    {
        Veterinarian veterinarian = this.veterinarianRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        VeterinarianResponseDTO output = VeterinarianMapper.toDTO(veterinarian);
        
        return output;
    }

    public VeterinarianResponseDTO store (VeterinarianRequestDTO veterinarianRequestDTO)
    {
        this.veterinarianValidator.validate(veterinarianRequestDTO);

        Veterinarian veterinarian = VeterinarianMapper.toEntity(veterinarianRequestDTO);
        User user = veterinarian.getUser();
 
        System.out.println(user.getName());

        this.userRepository.save(user);
        this.veterinarianRepository.save(veterinarian);

        VeterinarianResponseDTO output = VeterinarianMapper.toDTO(veterinarian);

        return output;
    }

    public VeterinarianResponseDTO update (Long id, VeterinarianRequestDTO veterinarianRequestDTO)
    {
        Veterinarian veterinarian = this.veterinarianRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
        User user = this.userRepository.findById(veterinarian.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        user.setName(veterinarianRequestDTO.getName());
        user.setPhone(veterinarianRequestDTO.getPhone());

        this.userRepository.save(user);

        VeterinarianResponseDTO output = VeterinarianMapper.toDTO(veterinarian);

        return output;
    }

    public void delete (Long id)
    {

        Veterinarian veterinarian = this.veterinarianRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
        User user = this.userRepository.findById(veterinarian.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        this.veterinarianRepository.delete(veterinarian);
        this.userRepository.delete(user);
    }
}
