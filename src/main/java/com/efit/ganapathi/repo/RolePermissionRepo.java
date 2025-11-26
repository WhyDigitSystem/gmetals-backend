package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.RolesPermissionHeaderVO;
import com.efit.ganapathi.entity.RolesPermissionVO;

@Repository
public interface RolePermissionRepo extends JpaRepository<RolesPermissionVO, Long> {

	List<RolesPermissionVO> findByRolesPermissionHeaderVO(RolesPermissionHeaderVO vo);


}