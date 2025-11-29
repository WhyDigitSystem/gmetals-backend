package com.efit.ganapathi.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.ChangePasswordFormDTO;
import com.efit.ganapathi.dto.ExporterDTO;
import com.efit.ganapathi.dto.LoginFormDTO;
import com.efit.ganapathi.dto.RefreshTokenDTO;
import com.efit.ganapathi.dto.ResetPasswordDTO;
import com.efit.ganapathi.dto.ResetPasswordFormDTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.dto.ResponsibilityDTO;
import com.efit.ganapathi.dto.RolesDTO;
import com.efit.ganapathi.dto.SignUpFormDTO;
import com.efit.ganapathi.dto.UserResponseDTO;
import com.efit.ganapathi.entity.ExporterVO;
import com.efit.ganapathi.entity.ResponsibilityVO;
import com.efit.ganapathi.entity.RolesVO;
import com.efit.ganapathi.entity.UserVO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface AuthService {

	public ResponseDTO signup(SignUpFormDTO signUpRequest) throws ApplicationException;

	public UserResponseDTO login(LoginFormDTO loginRequest, HttpServletRequest request) throws ApplicationException;

	public void logout(String userName);

	public void changePassword(ChangePasswordFormDTO changePasswordRequest);

	public void resetPassword(ResetPasswordFormDTO resetPasswordRequest);

	public RefreshTokenDTO getRefreshToken(String userName, String tokenId) throws ApplicationException;

	List<Map<String, Object>> getResponsibilityForRolesByOrgId(Long orgId);

	Map<String, Object> createUpdateRoles(RolesDTO rolesDTO) throws ApplicationException;

	public List<RolesVO> getAllRoles(Long orgId);

	public List<RolesVO> getAllActiveRoles(Long orgId);

	RolesVO getRolesById(Long id) throws ApplicationException;

	Map<String, Object> createUpdateResponsibilities(ResponsibilityDTO responsibilityDTO) throws ApplicationException;

	public List<ResponsibilityVO> getAllResponsibility(Long orgId);

	public List<ResponsibilityVO> getAllActiveResponsibility(Long orgId);

	ResponsibilityVO getResponsibilityById(Long id) throws ApplicationException;

	List<UserVO> getAllUsersByOrgId(Long orgId);

	public UserVO getUserById(Long userId);

	public UserVO getUserByUserName(String userName);

//	public Map<String, Object> getUsersByOrgId(Long orgId, String branchcode, int pageNumber, int count, String search);

	Map<String, Object> getUsersByOrgId(Long orgId, String branchCode, String search, int page, int count);

	void checkUserExists(String email) throws ApplicationException;

	ResponseDTO resetPassword(ResetPasswordDTO req) throws ApplicationException;


}
