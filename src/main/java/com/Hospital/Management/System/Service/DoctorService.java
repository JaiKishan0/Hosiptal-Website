package com.Hospital.Management.System.Service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Doctor;
import com.Hospital.Management.System.Repositries.DoctorRepository;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;

@Service
public class DoctorService {
	@Autowired
    private DoctorRepository doctorRepository;

	// Create a new doctor
    public Doctor createDoctor(Doctor doctor) {
        if (doctor == null || doctor.getName() == null) {
            throw new NullPointerException();
        }
        return doctorRepository.save(doctor);
    }

    // Retrieve a doctor by ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).get();
//                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
    }

    // Retrieve all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Update a doctor
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);  // Will throw if not found
        doctor.setName(doctorDetails.getName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        return doctorRepository.save(doctor);
    }

    // Delete a doctor by ID
    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);  // Will throw if not found
        doctorRepository.delete(doctor);
    }
}
