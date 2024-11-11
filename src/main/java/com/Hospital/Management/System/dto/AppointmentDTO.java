package com.Hospital.Management.System.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long id;
    private Long patientId; // ID of the patient
    private Long doctorId; // ID of the doctor
    private LocalDateTime appointmentDate;
    private double fee; // Added fee to the DTO
	private Object setStatus;

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

	public Object getSetStatus() {
		return setStatus;
	}

	public void setSetStatus(Object setStatus) {
		this.setStatus = setStatus;
	}

	public boolean isAccepted() {
		return false;
	}

	public boolean isCompleted() {
		return false;
	}

}
