package com.fatecmogidascruzes.petcare.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private Float amount;
    private PaymentMethod method;
    private Date date;
    private PaymentStatus status;
    private Invoice invoice;
}
