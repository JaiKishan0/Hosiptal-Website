package com.Hospital.Management.System.Service;
import com.Hospital.Management.System.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Repositries.UserRepository;
import com.Hospital.Management.System.dto.UserDTO;
import com.Hospital.Management.System.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticateUser(UserDTO userDTO) {
        // Fetch the user by username from the database
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Compare passwords (note: use password hashing in production)
        if (userDTO.getPassword().equals(user.getPassword())) {
            // Generate and return JWT token if authentication is successful
            return jwtUtil.generateToken(userDTO.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
    }