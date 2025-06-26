package com.fatecmogidascruzes.petcare.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatecmogidascruzes.petcare.dtos.Customer.CustomerRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Customer.CustomerResponseDTO;
import com.fatecmogidascruzes.petcare.repositories.CustomerRepository;
import com.fatecmogidascruzes.petcare.responses.ApiResponse;
import com.fatecmogidascruzes.petcare.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    private CustomerService customerService;

    public CustomerController (CustomerService customerService, CustomerRepository repository)
    {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> index ()
    {
        List<CustomerResponseDTO> customers = this.customerService.index();
        ApiResponse<List<CustomerResponseDTO>> output = new ApiResponse<>(200, "Clientes encontrados", customers);

        return ResponseEntity.ok(output);
    }

    @GetMapping("{id}")
    public ApiResponse<CustomerResponseDTO> show (@PathVariable Long id)
    {
        CustomerResponseDTO customer = this.customerService.show(id);
        ApiResponse<CustomerResponseDTO> output = new ApiResponse<>(200, "Cliente encontrado", customer);

        return output;
    }

    @PostMapping
    public CustomerResponseDTO store (@RequestBody CustomerRequestDTO customerRequestDTO)
    {
        CustomerResponseDTO output = this.customerService.store(customerRequestDTO);

        return output;
    }

    @PutMapping("{id}")
    public CustomerResponseDTO update (@PathVariable Long id, @RequestBody CustomerRequestDTO customerRequestDTO)
    {
        CustomerResponseDTO output = this.customerService.update(id, customerRequestDTO);

        return output;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id)
    {
        this.customerService.delete(id);
    }
}
