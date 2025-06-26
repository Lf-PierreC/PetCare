package com.fatecmogidascruzes.petcare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fatecmogidascruzes.petcare.dtos.Customer.CustomerRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Customer.CustomerResponseDTO;
import com.fatecmogidascruzes.petcare.exceptions.ResourceNotFoundException;
import com.fatecmogidascruzes.petcare.mappers.CustomerMapper;
import com.fatecmogidascruzes.petcare.models.Customer;
import com.fatecmogidascruzes.petcare.models.User;
import com.fatecmogidascruzes.petcare.repositories.CustomerRepository;
import com.fatecmogidascruzes.petcare.repositories.UserRepository;
import com.fatecmogidascruzes.petcare.validators.CustomerValidator;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private CustomerValidator customerValidator;

    public CustomerService (CustomerRepository customerRepository, UserRepository userRepository, CustomerValidator customerValidator)
    {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.customerValidator = customerValidator;
    }

    public List<CustomerResponseDTO> index ()
    {
        List<Customer> customers = this.customerRepository.findAll();

        List<CustomerResponseDTO> output = customers.stream()
                                                    .map(CustomerMapper::toDTO)
                                                    .collect(Collectors.toList());

        return output;
    }

    public CustomerResponseDTO show (Long id)
    {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        CustomerResponseDTO output = CustomerMapper.toDTO(customer);
        
        return output;
    }

    public CustomerResponseDTO store (CustomerRequestDTO customerRequestDTO)
    {
        this.customerValidator.validate(customerRequestDTO);

        Customer customer = CustomerMapper.toEntity(customerRequestDTO);
        User user = customer.getUser();

        this.userRepository.save(user);
        this.customerRepository.save(customer);

        CustomerResponseDTO output = CustomerMapper.toDTO(customer);

        return output;
    }

    public CustomerResponseDTO update (Long id, CustomerRequestDTO customerRequestDTO)
    {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        User user = this.userRepository.findById(customer.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        user.setName(customerRequestDTO.getName());
        user.setPhone(customerRequestDTO.getPhone());

        this.userRepository.save(user);

        CustomerResponseDTO output = CustomerMapper.toDTO(customer);

        return output;
    }

    public void delete (Long id)
    {

        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        User user = this.userRepository.findById(customer.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        this.customerRepository.delete(customer);
        this.userRepository.delete(user);
    }
}
