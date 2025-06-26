package com.fatecmogidascruzes.petcare.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Appointment {
    private Date date;
    private AppointmentService service;
    private Pet pet;
    private AppointmentStatus status;
}