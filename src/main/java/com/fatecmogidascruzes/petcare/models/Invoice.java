package com.fatecmogidascruzes.petcare.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private Float amount;
    private Date date;
    private InvoiceStatus status;
    private Appointment appointment;
}
