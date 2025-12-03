package com.efit.ganapathi.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.EnquiryDTO;
import com.efit.ganapathi.dto.PackingListDTO;
import com.efit.ganapathi.entity.EnquiryVO;
import com.efit.ganapathi.entity.PackingListVO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface TransactionService {

	// Enquiry

	Map<String, Object> getAllEnquiryByOrgId(Long orgId, String search, int page, int size);

	EnquiryVO getEnquiryById(Long id);

	Map<String, Object> updateCreateEnquiry(@Valid EnquiryDTO enquiryDTO) throws ApplicationException;
	
	List<Map<String, Object>> getCustomerNameAndCode(Long orgId);

	List<Map<String, Object>> getAssignedAgent(Long orgId);

	List<Map<String, Object>> getEnquiryCount(Long orgId, String branchCode, String Type);
	
	//PackingList

	PackingListVO getPackingListById(Long id);

	Map<String, Object> getAllPackingListByOrgId(Long orgId, String search, int page, int size);

	Map<String, Object> updateCreatePackingList(PackingListDTO packingListDTO) throws ApplicationException;

}
