package com.Hospital.Management.System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Management.System.Service.AuthService;
import com.Hospital.Management.System.dto.UserDTO;
import com.Hospital.Management.System.util.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            // Authenticate the user and generate JWT token
            String token = authService.authenticateUser(userDTO);

            // Return the token in a well-structured response
            return ResponseEntity.ok().body(new ApiResponse("Login successful", token));

        } catch (Exception e) {
            // Return error response if authentication fails
            return ResponseEntity.status(401).body(new ApiResponse("Authentication failed: " + e.getMessage(), null));
        }
    }
}
