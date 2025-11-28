package com.efit.ganapathi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.BranchDTO;
import com.efit.ganapathi.dto.PortDTO;
import com.efit.ganapathi.dto.ProductDTO;
import com.efit.ganapathi.dto.VesselDTO;
import com.efit.ganapathi.entity.BranchVO;
import com.efit.ganapathi.entity.PortVO;
import com.efit.ganapathi.entity.ProductVO;
import com.efit.ganapathi.entity.VesselVO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface MasterService {

	// Branch
	List<BranchVO> getAllBranch(Long orgid);

	Optional<BranchVO> getBranchById(Long branchid);

	Map<String, Object> createUpdateBranch(BranchDTO branchDTO) throws Exception;

	void deleteBranch(Long branchid);

	// Product

	ProductVO getProductById(Long id);

	Map<String, Object> getAllProductByOrgId(Long orgId, String search, int page, int size);

	Map<String, Object> updateCreateProduct(@Valid ProductDTO productDTO) throws ApplicationException;
	
	//Vessel

	Map<String, Object> getAllVesselByOrgId(Long orgId, String search, int page, int size);

	VesselVO getVesselById(Long id);

	Map<String, Object> updateCreateVessel(@Valid VesselDTO vesselDTO) throws ApplicationException;
	
	//Port

	Map<String, Object> getAllPortByOrgId(Long orgId, String search, int page, int size);

	PortVO getPortById(Long id);

	Map<String, Object> updateCreatePort(@Valid PortDTO portDTO) throws ApplicationException;

}
