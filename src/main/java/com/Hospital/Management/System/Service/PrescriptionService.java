package com.Hospital.Management.System.Service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Prescription;
import com.Hospital.Management.System.Repositries.PrescriptionRepository;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;

@Service
public class PrescriptionService {

	 @Autowired
	    private PrescriptionRepository prescriptionRepository;

	    // Create a new prescription
	    public Prescription createPrescription(Prescription prescription) {
	        if (prescription == null || prescription.getDetails() == null) {
	            throw new NullPointerException();
	        }
	        return prescriptionRepository.save(prescription);
	    }

	    // Retrieve a prescription by ID
	    public Prescription getPrescriptionById(Long id) {
	        return prescriptionRepository.findById(id).get();
//	                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with ID: " + id));
	    }

	    // Retrieve all prescriptions
	    public List<Prescription> getAllPrescriptions() {
	        return prescriptionRepository.findAll();
	    }

	    // Update a prescription
	    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
	        Prescription prescription = getPrescriptionById(id);  // Will throw if not found
	        prescription.setDetails(prescriptionDetails.getDetails());
	        prescription.setAppointment(prescriptionDetails.getAppointment());
	        return prescriptionRepository.save(prescription);
	    }

	    // Delete a prescription by ID
	    public void deletePrescription(Long id) {
	        Prescription prescription = getPrescriptionById(id);  // Will throw if not found
	        prescriptionRepository.delete(prescription);
	    }
}
