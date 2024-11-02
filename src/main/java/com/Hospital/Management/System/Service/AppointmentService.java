package com.Hospital.Management.System.Service;

import com.Hospital.Management.System.Entity.Appointment;
import com.Hospital.Management.System.Entity.Doctor;
import com.Hospital.Management.System.Entity.Patient;
import com.Hospital.Management.System.Repositries.AppointmentRepository;
import com.Hospital.Management.System.Repositries.DoctorRepository;
import com.Hospital.Management.System.Repositries.PatientRepository;
import com.Hospital.Management.System.dto.AppointmentDTO;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // Convert Appointment entity to AppointmentDTO
    private AppointmentDTO convertToDto(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStatus(appointment.isCompleted() ? "Completed" : "Pending");
        dto.setFee(appointment.getFee());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setDoctorId(appointment.getDoctor().getId());
        return dto;
    }

    // Convert AppointmentDTO to Appointment entity
    private Appointment convertToEntity(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setFee(dto.getFee());
        appointment.setAccepted(dto.isAccepted());
        appointment.setCompleted(dto.isCompleted());

        // Set Patient and Doctor entities based on IDs
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + dto.getDoctorId()));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointment;
    }

    // Create a new appointment
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        if (dto == null || dto.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Appointment details cannot be null");
        }
        Appointment appointment = convertToEntity(dto);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDto(savedAppointment);
    }

    // Retrieve an appointment by ID
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        return convertToDto(appointment);
    }

    // Retrieve all appointments
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Update an appointment
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));

        if (dto.getAppointmentDate() != null) {
            appointment.setAppointmentDate(dto.getAppointmentDate());
        }
        if (dto.getFee() > 0) {
            appointment.setFee(dto.getFee());
        }
        if (dto.isAccepted() != appointment.isAccepted()) {
            appointment.setAccepted(dto.isAccepted());
        }
        if (dto.isCompleted() != appointment.isCompleted()) {
            appointment.setCompleted(dto.isCompleted());
        }

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToDto(updatedAppointment);
    }

    // Delete an appointment by ID
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        appointmentRepository.delete(appointment);
    }
}
