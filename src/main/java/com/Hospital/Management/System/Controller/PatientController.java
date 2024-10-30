package com.Hospital.Management.System.Controller;

import com.Hospital.Management.System.dto.PatientDTO;
import com.Hospital.Management.System.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Endpoint to get all patients
    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Endpoint to get a patient by ID
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // Endpoint to create a new patient
    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    // Endpoint to update a patient
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }
}
