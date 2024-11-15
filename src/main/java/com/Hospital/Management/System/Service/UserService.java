package com.Hospital.Management.System.Service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Hospital.Management.System.Entity.Role;
import com.Hospital.Management.System.Entity.User;
import com.Hospital.Management.System.Repositries.UserRepository;
import com.Hospital.Management.System.dto.UserDTO;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // Injecting passwordEncoder via constructor

    // Constructor-based injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user by username
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        // Return the user with authorities (role)
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    public UserDTO registerUser(UserDTO userDTO) {
        // Check if username already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Create new User object and set its properties
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Use the injected PasswordEncoder
//        user.setRole(Role.valueOf(userDTO.getRole().toUpperCase()));  // Role mapping

        // Save the user to the repository
        User savedUser = userRepository.save(user);
        
        // Convert to DTO and return
        return convertToDTO(savedUser);
    }

    // Convert User entity to UserDTO
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
//        userDTO.setRole(user.getRole().name());  // Ensure that role is mapped correctly
        return userDTO;
    }
}
