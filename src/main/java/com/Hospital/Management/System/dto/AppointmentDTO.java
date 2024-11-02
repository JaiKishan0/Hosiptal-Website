package com.Hospital.Management.System.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long id;
    private Long patientId; // ID of the patient
    private Long doctorId; // ID of the doctor
    private LocalDateTime appointmentDate;
    private double fee; // Added fee to the DTO

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId; // Ensure this method exists
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId; // Ensure this method exists
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public double getFee() {
        return fee; // Getter for fee
    }

    public void setFee(double fee) {
        this.fee = fee; // Setter for fee
    }

	public void setStatus(Object object) {
		this.setStatus = object;
	}
}
