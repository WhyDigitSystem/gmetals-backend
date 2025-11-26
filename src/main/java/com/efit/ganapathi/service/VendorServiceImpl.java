package com.efit.ganapathi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.VendorDTO;
import com.efit.ganapathi.dto.VendorDetailsDTO;
import com.efit.ganapathi.dto.VendorUsersDTO;
import com.efit.ganapathi.entity.VendorDetailsVO;
import com.efit.ganapathi.entity.VendorUsersVO;
import com.efit.ganapathi.entity.VendorVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.VendorDetailsRepo;
import com.efit.ganapathi.repo.VendorRepo;
import com.efit.ganapathi.repo.VendorUsersRepo;
import com.efit.ganapathi.security.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

	public static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImpl.class);

	@Autowired
	VendorRepo vendorRepo;
	
	@Autowired
	VendorUsersRepo vendorUsersRepo;
	
	@Autowired
	VendorDetailsRepo vendorDetailsRepo;
	
	@Override
	public Map<String, Object> createUpdateVendor(VendorDTO vendorDTO) throws ApplicationException {

	    VendorVO vendorVO;
	    String message;

	    if (ObjectUtils.isNotEmpty(vendorDTO.getId())) {

	        vendorVO = vendorRepo.findById(vendorDTO.getId())
	                .orElseThrow(() -> new ApplicationException("Invalid vendor Master details"));

	        vendorUsersRepo.deleteAll(vendorUsersRepo.findByVendorVO(vendorVO));
	        vendorDetailsRepo.deleteAll(vendorDetailsRepo.findByVendorVO(vendorVO));

	        vendorVO.setUpdatedBy(vendorDTO.getCreatedBy());
	        message = "Vendor Updated Successfully";

	    } else {

	        vendorVO = new VendorVO();
	        vendorVO.setCreatedBy(vendorDTO.getCreatedBy());
	        vendorVO.setUpdatedBy(vendorDTO.getCreatedBy());
	        message = "Vendor Created Successfully";
	    }

	    createUpdateVendorVOByVendorDTO(vendorDTO, vendorVO);

	    vendorRepo.save(vendorVO);

	    Map<String, Object> response = new HashMap<>();
	    response.put("vendorVO", vendorVO);
	    response.put("message", message);

	    return response;   // <-- Return directly, no nesting!
	}


	private void createUpdateVendorVOByVendorDTO(VendorDTO dto, VendorVO vo) {
		 vo.setVendorCode(dto.getVendorCode());
		    vo.setStatus(dto.getStatus());
		    vo.setOrganization(dto.getOrganization());
		    vo.setApprovalStatus(dto.getApprovalStatus());
		    vo.setPrimaryPhoneNumber(dto.getPrimaryPhoneNumber());
		    vo.setPrimaryEmail(dto.getPrimaryEmail());
		    vo.setAdditionalPhoneNumber(dto.getAdditionalPhoneNumber());
		    vo.setAdditionalEmails(dto.getAdditionalEmails());
		    vo.setGst(dto.getGst());
		    vo.setAddress(dto.getAddress());
		    vo.setAccountNumber(dto.getAccountNumber());
		    vo.setIfsc(dto.getIfsc());
		    vo.setAccountHolderName(dto.getAccountHolderName());

		    vo.setVendorType(dto.getVendorType());
		    vo.setAdvancePercent(dto.getAdvancePercent());
		    vo.setCreditPeriod(dto.getCreditPeriod());
		    vo.setTdsPercent(dto.getTdsPercent());
		    vo.setVendorSpotId(dto.getVendorSpotId());
		    vo.setVendoruuid(dto.getVendoruuid());
		    vo.setTags(dto.getTags());
		    vo.setPocName(dto.getPocName());
		    vo.setPocEmail(dto.getPocEmail());
		    vo.setPocNumber(dto.getPocNumber());


		    vo.setActive(dto.isActive());
		    vo.setOrgId(dto.getOrgId());
		    vo.setBranchCode(dto.getBranchCode());
		    vo.setBranch(dto.getBranch());
       
		List<VendorUsersVO> detailsList = new ArrayList<>();

		if (dto.getVendorUsersDTO() != null && !dto.getVendorUsersDTO().isEmpty()) {
			for (VendorUsersDTO detailDTO : dto.getVendorUsersDTO()) {
				VendorUsersVO detailVO = new VendorUsersVO();

				detailVO.setUsers(detailDTO.getUsers());
				
				detailVO.setVendorVO(vo); // Set parent reference
				detailsList.add(detailVO);
			}
		}
		
		List<VendorDetailsVO> detailsLists = new ArrayList<>();

		if (dto.getVendorDetailsDTO() != null && !dto.getVendorDetailsDTO().isEmpty()) {
			for (VendorDetailsDTO detailDTO : dto.getVendorDetailsDTO()) {
				VendorDetailsVO detailVO = new VendorDetailsVO();

				detailVO.setEffectiveFrom(detailDTO.getEffectiveFrom());
				detailVO.setEffectioveTo(detailDTO.getEffectioveTo());
				
				detailVO.setVendorVO(vo); // Set parent reference
				detailsLists.add(detailVO);
			}
		}

		vo.setVendorUsersVO(detailsList);
		vo.setVendorDetailsVO(detailsLists);

	}

	
	
	@Override
	public Map<String, Object> getVendorByOrgId(Long orgId, String branchCode, String search, int page, int count) {
	    if (search == null) search = "";

	    Pageable pageable = PageRequest.of(page - 1, count); // Page 1 → offset 0
	    Page<Map<String, Object>> vendorPage = vendorRepo.getVendorByOrgId(orgId, branchCode, search, pageable);

	    Map<String, Object> result = new HashMap<>();
	    result.put("totalCount", vendorPage.getTotalElements());
	    result.put("totalPages", vendorPage.getTotalPages());
	    result.put("currentPage", page);
	    result.put("users", vendorPage.getContent());
	    return result;
	}

}
