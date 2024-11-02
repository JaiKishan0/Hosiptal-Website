package com.Hospital.Management.System.Service;

import com.Hospital.Management.System.Entity.Appointment;
import com.Hospital.Management.System.Entity.Doctor; // Import Doctor entity
import com.Hospital.Management.System.Entity.Prescription;
import com.Hospital.Management.System.exceptions.ResourceNotFoundException;
import com.Hospital.Management.System.Repositries.AppointmentRepository;
import com.Hospital.Management.System.Repositries.DoctorRepository; // Import DoctorRepository
import com.Hospital.Management.System.Repositries.PrescriptionRepository;
import com.Hospital.Management.System.dto.DoctorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository; // Add DoctorRepository

    // 1. View Appointments
    public List<Appointment> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // 2. Write Prescription
    @Transactional
    public Prescription writePrescription(Long appointmentId, String medicationDetails, String dosageInstructions) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        // Validate medication details and dosage instructions
        validatePrescriptionDetails(medicationDetails, dosageInstructions);

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setPatient(appointment.getPatient());
        prescription.setMedicationDetails(medicationDetails);
        prescription.setDosageInstructions(dosageInstructions);

        return prescriptionRepository.save(prescription);
    }

    // 3. Calculate Total Earnings
    public double calculateTotalEarnings(Long doctorId) {
        List<Appointment> completedAppointments = appointmentRepository.findByDoctorIdAndCompletedTrue(doctorId);
        return completedAppointments.stream().mapToDouble(Appointment::getFee).sum();
    }

    // 4. Generate Bill
    public double generateBill(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (!appointment.isCompleted()) {
            throw new IllegalStateException("Appointment not completed. Cannot generate bill.");
        }

        return appointment.getFee();
    }

    private void validatePrescriptionDetails(String medicationDetails, String dosageInstructions) {
        // Add validation logic here
        if (medicationDetails == null || medicationDetails.isEmpty()) {
            throw new IllegalArgumentException("Medication details cannot be empty");
        }
        if (dosageInstructions == null || dosageInstructions.isEmpty()) {
            throw new IllegalArgumentException("Dosage instructions cannot be empty");
        }
    }

    // Update Prescription
    public Prescription updatePrescription(Long prescriptionId, String medicationDetails, String dosageInstructions) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found"));

        prescription.setMedicationDetails(medicationDetails);
        prescription.setDosageInstructions(dosageInstructions);
        
        return prescriptionRepository.save(prescription);
    }

    // Get Prescription by Appointment ID
    public Prescription getPrescriptionByAppointmentId(Long appointmentId) {
        // Assuming each appointment has one prescription
        return prescriptionRepository.findByAppointment_Id(appointmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this appointment"));
    }
    
    // Delete Prescription 
    public void deletePrescription(Long prescriptionId) {
        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new ResourceNotFoundException("Prescription not found");
        }
        prescriptionRepository.deleteById(prescriptionId);
    }
    
    // Method to accept an appointment
    public Appointment acceptAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setAccepted(true); // Set the accepted status
        return appointmentRepository.save(appointment); // Save the updated appointment
    }


    // CRUD Operations for Doctor

    // Create Doctor
    // 1. Save Doctor
    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        // Convert DTO to Entity
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setContact(doctorDTO.getContact());

        // Save the Doctor entity
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Return saved Doctor as DTO
        return new DoctorDTO(savedDoctor.getId(), savedDoctor.getName(), savedDoctor.getSpecialization(), savedDoctor.getContact());
    }

    // Get Doctor by ID
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
    }

    // Update Doctor
    public Doctor updateDoctor(Long doctorId, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // Update doctor fields
        doctor.setName(doctorDetails.getName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setContact(doctorDetails.getContact());
        // Add more fields as necessary

        return doctorRepository.save(doctor);
    }

    // Delete Doctor
    public void deleteDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found");
        }
        doctorRepository.deleteById(doctorId);
    }
    
    //Get all Doctors
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getContact()
        )).collect(Collectors.toList());
    }
}
