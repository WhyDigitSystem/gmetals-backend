package com.efit.ganapathi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efit.ganapathi.entity.TokenVO;

public interface TokenRepo extends JpaRepository<TokenVO, String>{

}
