package com.Hospital.Management.System.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Patient;
import com.Hospital.Management.System.Entity.Prescription;
import com.Hospital.Management.System.Repositries.PatientRepository;
import com.Hospital.Management.System.dto.PatientDTO;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
    
    public byte[] generatePrescriptionPDF(Prescription prescription) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Initialize PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
			PdfWriter.getInstance(document, byteArrayOutputStream);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        document.open();

        // Add content to the PDF
        try {
			document.add(new Paragraph("Prescription ID: " + prescription.getId()));
	        document.add(new Paragraph("Medication Details: " + prescription.getMedicationDetails()));
	        document.add(new Paragraph("Dosage Instructions: " + prescription.getDosageInstructions()));
	        document.add(new Paragraph("Additional Details: " + (prescription.getDetails() != null ? prescription.getDetails() : "N/A")));
	        document.add(new Paragraph("Doctor: " + prescription.getDoctor().getName()));
	        document.add(new Paragraph("Patient: " + prescription.getPatient().getName()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
