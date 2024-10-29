package com.Hospital.Management.System.Service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hospital.Management.System.Entity.Patient;
import com.Hospital.Management.System.Repositries.PatientRepository;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;

@Service
public class PatientService {

	@Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    public Patient createPatient(Patient patient) {
        if (patient == null || patient.getName() == null) {
            throw new NullPointerException();
        }
        return patientRepository.save(patient);
    }

    // Retrieve a patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).get();
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
    }

    // Retrieve all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Update a patient
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);  // Will throw if not found
        patient.setName(patientDetails.getName());
        patient.setEmail(patientDetails.getEmail());
        patient.setContact(patientDetails.getContact());
        return patientRepository.save(patient);
    }

    // Delete a patient by ID
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);  // Will throw if not found
        patientRepository.delete(patient);
    }
}
