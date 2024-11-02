package com.Hospital.Management.System.Repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hospital.Management.System.Entity.Doctor;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	  List<Doctor> findAll();


}
