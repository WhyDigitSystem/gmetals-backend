package com.efit.ganapathi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efit.ganapathi.common.CommonConstant;
import com.efit.ganapathi.common.UserConstants;
import com.efit.ganapathi.dto.BranchDTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.dto.VechicleDTO;
import com.efit.ganapathi.entity.BranchVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.service.MasterService;

@CrossOrigin
@RestController
@RequestMapping("/api/master")
public class MasterController extends BaseController {

	@Autowired
	MasterService masterService;

	public static final Logger LOGGER = LoggerFactory.getLogger(MasterController.class);

	// Branch
		@GetMapping("/branch")
		public ResponseEntity<ResponseDTO> getAllBranch(@RequestParam Long orgid) {
			String methodName = "getAllBranch()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<BranchVO> branchVO = new ArrayList<>();
			try {
				branchVO = masterService.getAllBranch(orgid);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch information get successfully");
				responseObjectsMap.put("branchVO", branchVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap, "Branch information receive failed", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);
		}

		@GetMapping("/branch/{branchid}")
		public ResponseEntity<ResponseDTO> getBranchById(@PathVariable Long branchid) {
			String methodName = "getBranchById()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			BranchVO branchVO = null;
			try {
				branchVO = masterService.getBranchById(branchid).orElse(null);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isEmpty(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch found by ID");
				responseObjectsMap.put("Branch", branchVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Branch not found for ID: " + branchid;
				responseDTO = createServiceResponseError(responseObjectsMap, "Branch not found", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);
		}
	 
		@PutMapping("/createUpdateBranch")
		public ResponseEntity<ResponseDTO> createUpdateBranch(@RequestBody BranchDTO branchDTO) {
			String methodName = "createBranch()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			try {
				Map<String, Object> createdBranchVO = masterService.createUpdateBranch(branchDTO);
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,createdBranchVO.get("message"));
				responseObjectsMap.put("branchVO", createdBranchVO.get("branchVO"));
				responseDTO = createServiceResponse(responseObjectsMap);
			} catch (Exception e) {
		        errorMsg = e.getMessage();
		        LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		        responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		    }
		    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		    return ResponseEntity.ok().body(responseDTO);
		}
		

		
		
//		@PutMapping("/createUpdateVechile")
//		public ResponseEntity<ResponseDTO> createUpdateVechile(@RequestBody VechicleDTO vechicleDTO) throws ApplicationException {
//		    String methodName = "createUpdateVechile()";
//		    LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
//
//		    Map<String, Object> responseObjectsMap = new HashMap<>();
//		    ResponseDTO responseDTO;
//
//		    try {
//		        // Call service method
//		        Map<String, Object> vechicleVO = masterService.createUpdateVechile(vechicleDTO);
//
//		        // Extract message and data
//		        Object salaryStructureVO = vechicleVO.get("paramObjectsMap");
//		        String message = (String) vechicleVO.getOrDefault("message", "vechicle completed successfully.");
//
//		        // Populate response map
//		        responseObjectsMap.put("vechicleVO", vechicleVO);
//		        responseObjectsMap.put(CommonConstant.STRING_MESSAGE, message);
//
//
//		        // Create structured response
//		        responseDTO = createServiceResponse(responseObjectsMap);
//		    } catch (Exception e) {
//		        LOGGER.error("{} - Unexpected Error: {}", methodName, e.getMessage(), e);
//		        responseDTO = createServiceResponseError(responseObjectsMap, "Unexpected Error", "Something went wrong.");
//		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
//		    }
//
//		    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
//		    return ResponseEntity.ok(responseDTO);
//		}

		 }


