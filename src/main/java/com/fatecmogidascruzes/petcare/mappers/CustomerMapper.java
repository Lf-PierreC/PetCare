package com.fatecmogidascruzes.petcare.mappers;

import com.fatecmogidascruzes.petcare.dtos.Customer.CustomerRequestDTO;
import com.fatecmogidascruzes.petcare.dtos.Customer.CustomerResponseDTO;
import com.fatecmogidascruzes.petcare.models.Customer;
import com.fatecmogidascruzes.petcare.models.User;

public class CustomerMapper {
    public static Customer toEntity (CustomerRequestDTO dto)
    {
        User user = new User();
        Customer customer = new Customer();

        user.setName(dto.getName());
        user.setPhone(dto.getPhone());

        customer.setUser(user);

        return customer;
    }

    public static CustomerResponseDTO toDTO (Customer customer)
    {
        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(customer.getId());
        dto.setName(customer.getUser().getName());
        dto.setPhone(customer.getUser().getPhone());
        dto.setCreatedAt(customer.getCreatedAt());

        return dto;
    }
}
