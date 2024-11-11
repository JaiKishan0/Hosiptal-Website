package com.Hospital.Management.System.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Management.System.Entity.Appointment;
import com.Hospital.Management.System.Entity.Doctor;
import com.Hospital.Management.System.Entity.Patient;
import com.Hospital.Management.System.Service.AppointmentService;
import com.Hospital.Management.System.Service.DoctorService;
import com.Hospital.Management.System.Service.PatientService;
import com.Hospital.Management.System.Service.PrescriptionService;
import com.Hospital.Management.System.dto.AppointmentDTO;
import com.Hospital.Management.System.dto.DoctorDTO;
import com.Hospital.Management.System.dto.PatientDTO;
import com.Hospital.Management.System.dto.PrescriptionDTO;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
    private PatientService patientService;
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private PrescriptionService prescriptionService;

 // Patient Endpoints
    @GetMapping("/patients/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/patients")
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    // Doctor Endpoints
    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/doctors")
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO savedDoctor = doctorService.saveDoctor(doctorDTO);
        return ResponseEntity.ok(savedDoctor); // Return the saved doctor
    }

    @DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    // Appointment Endpoints
    @GetMapping("/appointments/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/appointments")
    public List<AppointmentDTO> getAllAppointments() {
        return (List<AppointmentDTO>) appointmentService.getAllAppointments();
    }

    @PostMapping("/appointments")
    public AppointmentDTO createAppointment (@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.createAppointment(appointmentDTO);
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    // Prescription Endpoints
    @GetMapping("/prescriptions/{id}")
    public PrescriptionDTO getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @GetMapping("/prescriptions")
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @PostMapping("/prescriptions")
    public PrescriptionDTO createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        return prescriptionService.createPrescription(prescriptionDTO);
    }

    @DeleteMapping("/prescriptions/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
}
