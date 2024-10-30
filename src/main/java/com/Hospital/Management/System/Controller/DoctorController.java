package com.Hospital.Management.System.Controller;

import com.Hospital.Management.System.Entity.Appointment;
import com.Hospital.Management.System.Entity.Prescription;
import com.Hospital.Management.System.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // View Appointments
    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable Long doctorId) {
        List<Appointment> appointments = doctorService.getDoctorAppointments(doctorId);
        return ResponseEntity.ok(appointments);
    }

    // Write Prescription
    @PostMapping("/appointments/{appointmentId}/prescription")
    public ResponseEntity<Prescription> writePrescription(
            @PathVariable Long appointmentId,
            @RequestParam String medicationDetails,
            @RequestParam String dosageInstructions) {

        Prescription prescription = doctorService.writePrescription(appointmentId, medicationDetails, dosageInstructions);
        return ResponseEntity.ok(prescription);
    }

    // Get Prescription Details
    @GetMapping("/appointments/{appointmentId}/prescription")
    public ResponseEntity<Prescription> getPrescriptionDetails(@PathVariable Long appointmentId) {
        Prescription prescription = doctorService.getPrescriptionByAppointmentId(appointmentId);
        return ResponseEntity.ok(prescription);
    }

    // Update Prescription
    @PutMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<Prescription> updatePrescription(
            @PathVariable Long prescriptionId,
            @RequestParam String medicationDetails,
            @RequestParam String dosageInstructions) {

        Prescription updatedPrescription = doctorService.updatePrescription(prescriptionId, medicationDetails, dosageInstructions);
        return ResponseEntity.ok(updatedPrescription);
    }

    // Delete Prescription
    @DeleteMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long prescriptionId) {
        doctorService.deletePrescription(prescriptionId);
        return ResponseEntity.noContent().build();
    }

    // Calculate Total Earnings
    @GetMapping("/{doctorId}/earnings")
    public ResponseEntity<Double> getTotalEarnings(@PathVariable Long doctorId) {
        double earnings = doctorService.calculateTotalEarnings(doctorId);
        return ResponseEntity.ok(earnings);
    }

    // Generate Bill for Patient
    @GetMapping("/appointments/{appointmentId}/bill")
    public ResponseEntity<Double> generateBill(@PathVariable Long appointmentId) {
        double bill = doctorService.generateBill(appointmentId);
        return ResponseEntity.ok(bill);
    }
}
