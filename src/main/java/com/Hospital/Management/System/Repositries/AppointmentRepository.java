package com.Hospital.Management.System.Repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.Management.System.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>  {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDoctorIdAndCompletedTrue(Long doctorId);
}
