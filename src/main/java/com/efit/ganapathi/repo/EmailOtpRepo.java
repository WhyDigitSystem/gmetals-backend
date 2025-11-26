package com.efit.ganapathi.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efit.ganapathi.entity.EmailOtpEntity;

public interface EmailOtpRepo extends JpaRepository<EmailOtpEntity, Long> {
	
    Optional<EmailOtpEntity> findByEmail(String email);  // ← REQUIRED

    Optional<EmailOtpEntity> findByEmailAndVerified(String email, boolean verified);
}
