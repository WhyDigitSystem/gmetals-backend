package com.efit.ganapathi.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.RolesPermissionHeaderDTO;
import com.efit.ganapathi.entity.RolesPermissionHeaderVO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface RolesResponsibilitiesService {

	List<RolesPermissionHeaderVO> getRolesPermissionHeaderByRoleandOrgid(String role, Long orgid);

	Map<String, Object> createUpdateRoleScreenPermission(RolesPermissionHeaderDTO rolesPermissionHeaderDTO) throws ApplicationException;

}
