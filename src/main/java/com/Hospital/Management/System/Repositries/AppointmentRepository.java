package com.Hospital.Management.System.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.Management.System.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>  {

}
