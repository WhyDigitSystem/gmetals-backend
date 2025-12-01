package com.efit.ganapathi.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.Form1DTO;
import com.efit.ganapathi.dto.Form6DTO;
import com.efit.ganapathi.dto.Form9DTO;
import com.efit.ganapathi.entity.Form1VO;
import com.efit.ganapathi.entity.Form6VO;
import com.efit.ganapathi.entity.Form9VO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface FormService {

	// Form9

	Map<String, Object> getAllForm9ByOrgId(Long orgId, String search, int page, int size);

	Form9VO getForm9ById(Long id);

	Map<String, Object> updateCreateForm9(Form9DTO form9dto) throws ApplicationException;
	
	//Form6

	Map<String, Object> getAllForm6ByOrgId(Long orgId, String search, int page, int size);

	Form6VO getForm6ById(Long id);

	Map<String, Object> updateCreateForm6(Form6DTO form6DTO) throws ApplicationException;
	
	//Form1

	Map<String, Object> getAllForm1ByOrgId(Long orgId, String search, int page, int size);

	Form1VO getForm1ById(Long id);

	Map<String, Object> updateCreateForm1(Form1DTO form1DTO) throws ApplicationException;

}
