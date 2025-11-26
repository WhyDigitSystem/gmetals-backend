package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efit.ganapathi.entity.UserLoginRolesVO;
import com.efit.ganapathi.entity.UserVO;


public interface UserLoginRolesRepo extends JpaRepository<UserLoginRolesVO, Long> {

//	List<UserLoginRolesVO> findByUserVO(UserVO userVO);

//	UserLoginRolesVO findByUserVO_EmployeeCodeAndUserVO_OrgId(String employeeCode, Long orgId);

}