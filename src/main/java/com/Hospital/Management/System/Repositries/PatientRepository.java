package com.Hospital.Management.System.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.Management.System.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
