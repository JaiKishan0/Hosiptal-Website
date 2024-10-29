package com.Hospital.Management.System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Management.System.Entity.Appointment;
import com.Hospital.Management.System.Service.AppointmentService;

@RequestMapping("api/v1")
@RestController
public class MyController {
	
	@Autowired
	public AppointmentService appointmentService;
	
	@PostMapping("/create-appointment")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment)
	{
		Appointment appointment2 = appointmentService.createAppointment(appointment);
		return ResponseEntity.ok(appointment2);
	}
	
	
	
	

}
