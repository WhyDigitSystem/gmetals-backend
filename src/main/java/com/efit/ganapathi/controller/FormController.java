package com.efit.ganapathi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efit.ganapathi.common.CommonConstant;
import com.efit.ganapathi.common.UserConstants;
import com.efit.ganapathi.dto.Form1DTO;
import com.efit.ganapathi.dto.Form6DTO;
import com.efit.ganapathi.dto.Form9DTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.entity.Form1VO;
import com.efit.ganapathi.entity.Form6VO;
import com.efit.ganapathi.entity.Form9VO;
import com.efit.ganapathi.service.FormService;

@CrossOrigin
@RestController
@RequestMapping("/api/form")
public class FormController extends BaseController {

	@Autowired
	FormService formService;

	public static final Logger LOGGER = LoggerFactory.getLogger(FormController.class);

	@PutMapping("/updateCreateForm9")
	public ResponseEntity<ResponseDTO> updateCreateForm9(@Valid @RequestBody Form9DTO form9DTO) {
		String methodName = "updateCreateForm9()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> form9VO = formService.updateCreateForm9(form9DTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, form9VO.get("message"));
			responseObjectsMap.put("form9VO", form9VO.get("form9VO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllForm9ByOrgId")
	public ResponseEntity<ResponseDTO> getAllForm9ByOrgId(@RequestParam Long orgId,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		String methodName = "getAllForm9ByOrgId()";
		LOGGER.debug("Starting {}", methodName);

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> form9VO = formService.getAllForm9ByOrgId(orgId, search, page, size);
			responseMap.put("message", "Form9 retrieved successfully");
			responseMap.put("form9VO", form9VO);
			responseDTO = createServiceResponse(responseMap);
		} catch (Exception e) {
			LOGGER.error("Error in {}: {}", methodName, e.getMessage());
			responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
		}

		LOGGER.debug("Ending {}", methodName);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/getForm9ById")
	public ResponseEntity<ResponseDTO> getForm9ById(@RequestParam Long id) {
		String methodName = "getForm9ById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Form9VO form9VO = new Form9VO();
		try {
			form9VO = formService.getForm9ById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Form9 get successfully By id");
			responseObjectsMap.put("form9VO", form9VO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Form9 information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// form6

	@PutMapping("/updateCreateForm6")
	public ResponseEntity<ResponseDTO> updateCreateForm6(@RequestBody Form6DTO form6DTO) {
		String methodName = "updateCreateForm6()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> form6VO = formService.updateCreateForm6(form6DTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, form6VO.get("message"));
			responseObjectsMap.put("form6VO", form6VO.get("form6VO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllForm6ByOrgId")
	public ResponseEntity<ResponseDTO> getAllForm6ByOrgId(@RequestParam Long orgId,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		String methodName = "getAllForm6ByOrgId()";
		LOGGER.debug("Starting {}", methodName);

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> form6VO = formService.getAllForm6ByOrgId(orgId, search, page, size);
			responseMap.put("message", "Form6 retrieved successfully");
			responseMap.put("form6VO", form6VO);
			responseDTO = createServiceResponse(responseMap);
		} catch (Exception e) {
			LOGGER.error("Error in {}: {}", methodName, e.getMessage());
			responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
		}

		LOGGER.debug("Ending {}", methodName);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/getForm6ById")
	public ResponseEntity<ResponseDTO> getForm6ById(@RequestParam Long id) {
		String methodName = "getForm6ById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Form6VO form6VO = new Form6VO();
		try {
			form6VO = formService.getForm6ById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Form6 get successfully By id");
			responseObjectsMap.put("form6VO", form6VO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Form6 information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// form1

	@PutMapping("/updateCreateForm1")
	public ResponseEntity<ResponseDTO> updateCreateForm1(@RequestBody Form1DTO form1DTO) {
		String methodName = "updateCreateForm1()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> form1VO = formService.updateCreateForm1(form1DTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, form1VO.get("message"));
			responseObjectsMap.put("form1VO", form1VO.get("form1VO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllForm1ByOrgId")
	public ResponseEntity<ResponseDTO> getAllForm1ByOrgId(@RequestParam Long orgId,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		String methodName = "getAllForm1ByOrgId()";
		LOGGER.debug("Starting {}", methodName);

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> form1VO = formService.getAllForm1ByOrgId(orgId, search, page, size);
			responseMap.put("message", "Form9 retrieved successfully");
			responseMap.put("form1VO", form1VO);
			responseDTO = createServiceResponse(responseMap);
		} catch (Exception e) {
			LOGGER.error("Error in {}: {}", methodName, e.getMessage());
			responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
		}

		LOGGER.debug("Ending {}", methodName);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/getForm1ById")
	public ResponseEntity<ResponseDTO> getForm1ById(@RequestParam Long id) {
		String methodName = "getForm1ById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Form1VO form1VO = new Form1VO();
		try {
			form1VO = formService.getForm1ById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Form1 get successfully By id");
			responseObjectsMap.put("form1VO", form1VO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Form1 information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}
