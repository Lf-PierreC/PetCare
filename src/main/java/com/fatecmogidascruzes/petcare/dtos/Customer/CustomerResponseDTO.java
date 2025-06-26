package com.fatecmogidascruzes.petcare.dtos.Customer;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
}
