package com.efit.ganapathi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.BranchDTO;
import com.efit.ganapathi.dto.ProductDTO;
import com.efit.ganapathi.entity.BranchVO;
import com.efit.ganapathi.entity.ProductVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.BranchRepo;
import com.efit.ganapathi.repo.DepartmentRepo;
import com.efit.ganapathi.repo.DesignationLeaveRepo;
import com.efit.ganapathi.repo.DesignationRepo;
import com.efit.ganapathi.repo.ProductRepo;
import com.efit.ganapathi.repo.UserLoginRolesRepo;
import com.efit.ganapathi.repo.UserRepo;

@Service
public class MasterServiceImpl implements MasterService {
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);

	@Autowired
	BranchRepo branchRepo;

	@Autowired
	DesignationLeaveRepo designationLeaveRepo;

	@Autowired
	UserLoginRolesRepo userLoginRolesRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	DepartmentRepo departmentRepo;

	@Autowired
	DesignationRepo designationRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	PaginationService paginationService;

	// Branch

	@Override
	public List<BranchVO> getAllBranch(Long orgid) {
		return branchRepo.findAll(orgid);
	}

	@Override
	public Optional<BranchVO> getBranchById(Long branchid) {

		return branchRepo.findById(branchid);
	}

	@Override
	@Transactional
	public Map<String, Object> createUpdateBranch(BranchDTO branchDTO) throws Exception {
		BranchVO branchVO;
		String message = null;

		if (ObjectUtils.isEmpty(branchDTO.getId())) {
			// Check if the branch already exists for creation
			if (branchRepo.existsByBranchAndOrgId(branchDTO.getBranch(), branchDTO.getOrgId())) {
				String errorMessage = String.format("This Branch: %s Already Exists in This Organization",
						branchDTO.getBranch());
				throw new ApplicationException(errorMessage);
			}

			if (branchRepo.existsByBranchCodeAndOrgId(branchDTO.getBranchCode(), branchDTO.getOrgId())) {
				String errorMessage = String.format("This BranchCode: %s Already Exists in This Organization",
						branchDTO.getBranchCode());
				throw new ApplicationException(errorMessage);
			}

			// Create new branch
			branchVO = new BranchVO();
			branchVO.setCreatedBy(branchDTO.getCreatedBy());
			branchVO.setUpdatedBy(branchDTO.getCreatedBy());
			message = "Branch Created Successfully";
		} else {
			// Update existing branch
			branchVO = branchRepo.findById(branchDTO.getId())
					.orElseThrow(() -> new ApplicationException("Branch not found with id: " + branchDTO.getId()));

			branchVO.setUpdatedBy(branchDTO.getCreatedBy());

			if (!branchVO.getBranch().equalsIgnoreCase(branchDTO.getBranch())) {
				if (branchRepo.existsByBranchAndOrgId(branchDTO.getBranch(), branchDTO.getOrgId())) {
					String errorMessage = String.format("This Branch: %s Already Exists in This Organization",
							branchDTO.getBranch());
					throw new ApplicationException(errorMessage);
				}
				branchVO.setBranch(branchDTO.getBranch().toUpperCase());
			}

			if (!branchVO.getBranchCode().equalsIgnoreCase(branchDTO.getBranchCode())) {
				if (branchRepo.existsByBranchCodeAndOrgId(branchDTO.getBranchCode(), branchDTO.getOrgId())) {
					String errorMessage = String.format("This BranchCode: %s Already Exists in This Organization",
							branchDTO.getBranchCode());
					throw new ApplicationException(errorMessage);
				}
				branchVO.setBranchCode(branchDTO.getBranchCode().toUpperCase());
			}

			message = "Branch Updated Successfully";
		}

		getBranchVOFromBranchDTO(branchVO, branchDTO);
		branchRepo.save(branchVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("branchVO", branchVO);
		return response;
	}

	private void getBranchVOFromBranchDTO(BranchVO branchVO, BranchDTO branchDTO) {
		branchVO.setBranch(branchDTO.getBranch().toUpperCase());
		branchVO.setBranchCode(branchDTO.getBranchCode().toUpperCase());
		branchVO.setOrgId(branchDTO.getOrgId());
		branchVO.setAddressLine1(branchDTO.getAddressLine1());
		// branchVO.setAddressLine2(branchDTO.getAddressLine2());
		// branchVO.setPan(branchDTO.getPan());
		branchVO.setGstIn(branchDTO.getGstIn());
		branchVO.setContactPerson(branchDTO.getContactPerson());
		branchVO.setEmail(branchDTO.getEmail());
		branchVO.setPhone(branchDTO.getPhone());
		branchVO.setState(branchDTO.getState().toUpperCase());
		branchVO.setCity(branchDTO.getCity().toUpperCase());
		branchVO.setPinCode(branchDTO.getPinCode());
		branchVO.setCountry(branchDTO.getCountry().toUpperCase());
		// branchVO.setStateNo(branchDTO.getStateNo().toUpperCase());
		// branchVO.setStateCode(branchDTO.getStateCode().toUpperCase());
		// branchVO.setLccurrency(branchDTO.getLccurrency());
		branchVO.setCancelRemarks(branchDTO.getCancelRemarks());
		branchVO.setActive(branchDTO.isActive());
	}

	@Override
	public void deleteBranch(Long branchid) {
		branchRepo.deleteById(branchid);
	}

	// Product

	@Override
	public Map<String, Object> getAllProductByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("pname").ascending());
		Page<ProductVO> customerPage = productRepo.getAllProductByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public ProductVO getProductById(Long id) {

		return productRepo.getProductById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateProduct(@Valid ProductDTO productDTO) throws ApplicationException {

		ProductVO productVO = new ProductVO();

		String message;

		if (ObjectUtils.isNotEmpty(productDTO.getId())) {

			productVO = productRepo.findById(productDTO.getId())
					.orElseThrow(() -> new ApplicationException("Product Not Found!"));
			productVO.setUpdatedBy(productDTO.getCreatedBy());

			if (!productVO.getProductName().equalsIgnoreCase(productDTO.getProductName())) {
				if (productRepo.existsByProductNameAndOrgId(productDTO.getProductName(), productDTO.getOrgId())) {
					String errorMessage = String.format("This ProductName: %s Already Exists in This Organization",
							productDTO.getProductName());
					throw new ApplicationException(errorMessage);
				}
				productVO.setProductName(productDTO.getProductName().toUpperCase());
			}

			if (!productVO.getProductCode().equalsIgnoreCase(productDTO.getProductCode())) {
				if (productRepo.existsByProductCodeAndOrgId(productDTO.getProductCode(), productDTO.getOrgId())) {
					String errorMessage = String.format("This ProductCode: %s Already Exists in This Organization",
							productDTO.getProductName());
					throw new ApplicationException(errorMessage);
				}
				productVO.setProductName(productDTO.getProductName().toUpperCase());
			}

			message = "Enquiry Updated Successfully";
		} else {

			if (productRepo.existsByProductNameAndOrgId(productDTO.getProductName(), productDTO.getOrgId())) {
				String errorMessage = String.format("This ProductName: %s Already Exists in This Organization",
						productDTO.getProductName());
				throw new ApplicationException(errorMessage);
			}
			if (productRepo.existsByProductCodeAndOrgId(productDTO.getProductCode(), productDTO.getOrgId())) {
				String errorMessage = String.format("This ProductCode: %s Already Exists in This Organization",
						productDTO.getProductCode());
				throw new ApplicationException(errorMessage);
			}

			productVO.setUpdatedBy(productDTO.getCreatedBy());
			productVO.setCreatedBy(productDTO.getCreatedBy());

			message = "Enquiry Created Successfully";
		}

		createProductVOByProductDTO(productDTO, productVO);
		productRepo.save(productVO);
		Map<String, Object> response = new HashMap<>();
		response.put("productVO", productVO);
		response.put("message", message);
		return response;
	}

	private void createProductVOByProductDTO(@Valid ProductDTO productDTO, ProductVO productVO)
			throws ApplicationException {
		productVO.setProductName(productDTO.getProductName());
		productVO.setProductCode(productDTO.getProductCode());
		productVO.setCategory(productDTO.getCategory());
		productVO.setSubCategory(productDTO.getSubCategory());
		productVO.setUom(productDTO.getUom());
		productVO.setPrice(productDTO.getPrice());
		productVO.setActive(productDTO.isActive());
		productVO.setOrgId(productDTO.getOrgId());
		productVO.setCancel(productDTO.isCancel());

	}

}
