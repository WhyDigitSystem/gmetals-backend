package com.efit.ganapathi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.BranchDTO;
import com.efit.ganapathi.dto.VechicleDTO;
import com.efit.ganapathi.entity.BranchVO;

@Service
public interface MasterService {

	//Branch
	List<BranchVO> getAllBranch(Long orgid);

	Optional<BranchVO> getBranchById(Long branchid);

	Map<String, Object> createUpdateBranch(BranchDTO branchDTO) throws Exception;

	void deleteBranch(Long branchid);

//	Map<String, Object> createUpdateVechile(VechicleDTO vechicleDTO);
	
	




}
