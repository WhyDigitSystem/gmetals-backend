package com.efit.ganapathi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.VechicleVO;

@Repository
public interface VechicleRepo extends JpaRepository<VechicleVO, Long>{

}
