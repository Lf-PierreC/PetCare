package com.fatecmogidascruzes.petcare.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {
    private String name;
    private Date birth;
    private Breed breed;
    private Sex sex;
    private Size size;
    private Float weight;
    private String color;
    private Customer customer;
}
