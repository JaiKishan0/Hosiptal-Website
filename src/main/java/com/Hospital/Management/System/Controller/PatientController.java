package com.Hospital.Management.System.Controller;

import com.Hospital.Management.System.dto.PatientDTO;
import com.Hospital.Management.System.Service.PrescriptionService;
import com.Hospital.Management.System.Entity.Prescription;
import com.Hospital.Management.System.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PrescriptionService prescriptionService;

    // Endpoint to get all patients
    @GetMapping("/get-all-patients")
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Endpoint to get a patient by ID
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // Endpoint to create a new patient
    @PostMapping("/create-new")
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    // Endpoint to update a patient
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }
    
//    @GetMapping("/download-prescription/{prescriptionId}")
//    public ResponseEntity<byte[]> generatePrescriptionPDF(@PathVariable Long prescriptionId) throws IOException {
//        Prescription prescription = patientService.getPrescriptionById(prescriptionId);  // Retrieve the prescription from the service layer
//        
//        if (prescription == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        // Generate PDF from prescription
//        byte[] pdfBytes = prescriptionService.generatePrescriptionPDF(prescription);
//
//        // Set headers to indicate a file download
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=prescription_" + prescriptionId + ".pdf");
//
//        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//    }
}
