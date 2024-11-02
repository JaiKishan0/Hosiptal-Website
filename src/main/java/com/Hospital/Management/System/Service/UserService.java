package com.Hospital.Management.System.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Role;
import com.Hospital.Management.System.Entity.User;
import com.Hospital.Management.System.Repositries.UserRepository;
import com.Hospital.Management.System.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.valueOf(userDTO.getRole().toUpperCase())); // Convert string role to enum

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());	
        return userDTO;
    }

    // Additional methods for finding and managing users
}


