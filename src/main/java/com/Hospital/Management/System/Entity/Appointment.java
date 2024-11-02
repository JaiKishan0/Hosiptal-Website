package com.Hospital.Management.System.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments")
@Data // This annotation will generate getters and setters automatically
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDate; // Date and time of the appointment

    private boolean accepted; // Indicates if the appointment is accepted
    private boolean completed; // Indicates if the appointment is completed
    private double fee; // Represents the fee for the appointment

    @ManyToOne
    private Patient patient; // The patient for the appointment

    @ManyToOne
    private Doctor doctor; // The doctor for the appointment

    // Constructor for required fields
    public Appointment(LocalDateTime appointmentDate, double fee, Patient patient, Doctor doctor) {
        this.appointmentDate = appointmentDate;
        this.fee = fee;
        this.patient = patient;
        this.doctor = doctor;
        this.accepted = false; // Default value
        this.completed = false; // Default value
    }
}
