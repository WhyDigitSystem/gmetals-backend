package com.efit.ganapathi.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.Form9DTO;
import com.efit.ganapathi.entity.Form9VO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface FormService {

	// Form9

	Map<String, Object> getAllForm9ByOrgId(Long orgId, String search, int page, int size);

	Form9VO getForm9ById(Long id);

	Map<String, Object> updateCreateForm9(Form9DTO form9dto) throws ApplicationException;

}
