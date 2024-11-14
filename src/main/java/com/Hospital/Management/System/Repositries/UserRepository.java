package com.Hospital.Management.System.Repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.Management.System.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    
}


