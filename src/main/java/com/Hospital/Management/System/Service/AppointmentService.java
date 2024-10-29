package com.Hospital.Management.System.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Appointment;
import com.Hospital.Management.System.Repositries.AppointmentRepository;
@Service
public class AppointmentService {

	@Autowired
    private AppointmentRepository AppointmentRepository;

	    // Create a new appointment
	    public Appointment createAppointment(Appointment appointment) {
	        if (appointment == null || appointment.getAppointmentDate() == null) {
	            throw new NullPointerException();
	        }
	        return AppointmentRepository.save(appointment);
	    }

	    // Retrieve an appointment by ID
	    public Appointment getAppointmentById(Long id) {
	        return AppointmentRepository.findById(id).get();
//	                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: "));
	    }

	    // Retrieve all appointments
	    public List<Appointment> getAllAppointments() {
	        return AppointmentRepository.findAll();
	    }

	    // Update an appointment
	    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
	        Appointment appointment = getAppointmentById(id);  // Will throw if not found
	        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
	        appointment.setDoctor(appointmentDetails.getDoctor());
	        appointment.setPatient(appointmentDetails.getPatient());
	        return AppointmentRepository.save(appointment);
	    }

	    // Delete an appointment by ID
	    public void deleteAppointment(Long id) {
	        Appointment appointment = getAppointmentById(id);  // Will throw if not found
	        AppointmentRepository.delete(appointment);
	    }
}
