package com.Hospital.Management.System.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Patient;
import com.Hospital.Management.System.Repositries.PatientRepository;
import com.Hospital.Management.System.dto.PatientDTO;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Convert Patient entity to PatientDTO
    private PatientDTO convertToDto(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setContact(patient.getContact()); // Mapping contact
        return patientDTO;
    }

    // Convert PatientDTO to Patient entity
    private Patient convertToEntity(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setContact(patientDTO.getContact()); // Mapping contact
        return patient;
    }

    // Create a new patient
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        return convertToDto(patientRepository.save(patient));
    }

    // Retrieve a patient by ID
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
        return convertToDto(patient);
    }

    // Retrieve all patients
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Update a patient
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setContact(patientDTO.getContact());
        return convertToDto(patientRepository.save(patient));
    }

    // Delete a patient by ID
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
        patientRepository.delete(patient);
    }
}
