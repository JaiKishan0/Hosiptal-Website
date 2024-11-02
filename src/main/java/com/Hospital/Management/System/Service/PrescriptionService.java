package com.Hospital.Management.System.Service;

import com.Hospital.Management.System.Entity.Prescription;
import com.Hospital.Management.System.Repositries.PrescriptionRepository;
import com.Hospital.Management.System.dto.PrescriptionDTO;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    // Convert Prescription entity to PrescriptionDTO
    private PrescriptionDTO convertToDto(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setId(prescription.getId());
        prescriptionDTO.setMedication(prescription.getMedicationDetails());
        prescriptionDTO.setDosage(prescription.getDosageInstructions());
        prescriptionDTO.setInstructions(prescription.getDetails());
        prescriptionDTO.setAppointmentId(prescription.getAppointment().getId());
        prescriptionDTO.setDoctorId(prescription.getDoctor().getId());
        prescriptionDTO.setPatientId(prescription.getPatient().getId());
        return prescriptionDTO;
    }

    // Convert PrescriptionDTO to Prescription entity
    private Prescription convertToEntity(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionDTO.getId());
        prescription.setMedicationDetails(prescriptionDTO.getMedication());
        prescription.setDosageInstructions(prescriptionDTO.getDosage());
        prescription.setDetails(prescriptionDTO.getInstructions());
        return prescription;
    }

    // Create a new prescription
    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
        if (prescriptionDTO == null || prescriptionDTO.getMedication() == null) {
            throw new IllegalArgumentException("Prescription details cannot be null");
        }
        Prescription prescription = convertToEntity(prescriptionDTO);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return convertToDto(savedPrescription);
    }

    // Retrieve a prescription by ID
    public PrescriptionDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with ID: " + id));
        return convertToDto(prescription);
    }

    // Retrieve all prescriptions
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Update a prescription
    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with ID: " + id));
        
        if (prescriptionDTO.getMedication() != null) {
            prescription.setMedicationDetails(prescriptionDTO.getMedication());
        }
        if (prescriptionDTO.getDosage() != null) {
            prescription.setDosageInstructions(prescriptionDTO.getDosage());
        }
        if (prescriptionDTO.getInstructions() != null) {
            prescription.setDetails(prescriptionDTO.getInstructions());
        }

        Prescription updatedPrescription = prescriptionRepository.save(prescription);
        return convertToDto(updatedPrescription);
    }

    // Delete a prescription by ID
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with ID: " + id));
        prescriptionRepository.delete(prescription);
    }
}
