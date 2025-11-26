package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efit.ganapathi.entity.RolesResponsibilityVO;
import com.efit.ganapathi.entity.RolesVO;


public interface RolesResponsibilityRepo extends JpaRepository<RolesResponsibilityVO, Long>{

	List<RolesResponsibilityVO> findByRolesVO(RolesVO rolesVO);

}
