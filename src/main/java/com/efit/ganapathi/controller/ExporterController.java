package com.efit.ganapathi.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.efit.ganapathi.dto.ExporterDTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.entity.ExporterVO;
import com.efit.ganapathi.service.ExporterService;

@CrossOrigin
@RestController
@RequestMapping("/api/exporter")
public class ExporterController extends BaseController {

	@Autowired
	private ExporterService exporterService;

	public static final Logger LOGGER = LoggerFactory.getLogger(ExporterController.class);

	// ============================================================
	// VALIDATION (Updated for new shortName field)
	// ============================================================
	private void validateExporter(ExporterDTO dto) throws Exception {

		// Convert empty strings to NULL
		dto.setCompanyName(StringUtils.defaultIfBlank(dto.getCompanyName(), null));
		dto.setShortName(StringUtils.defaultIfBlank(dto.getShortName(), null));
		dto.setContactPerson(StringUtils.defaultIfBlank(dto.getContactPerson(), null));
		dto.setTaxId(StringUtils.defaultIfBlank(dto.getTaxId(), null));
		dto.setPhone(StringUtils.defaultIfBlank(dto.getPhone(), null));
		dto.setEmail(StringUtils.defaultIfBlank(dto.getEmail(), null));
		dto.setAddress(StringUtils.defaultIfBlank(dto.getAddress(), null));
		dto.setBranch(StringUtils.defaultIfBlank(dto.getBranch(), null));
		dto.setBranchCode(StringUtils.defaultIfBlank(dto.getBranchCode(), null));

		// Required fields
		if (dto.getCompanyName() == null)
			throw new Exception("Company Name is required");

		if (dto.getShortName() == null)
			throw new Exception("Short Name is required");

		if (dto.getEmail() == null)
			throw new Exception("Email is required");

		if (dto.getPhone() == null)
			throw new Exception("Phone Number is required");

		if (dto.getTaxId() == null)
			throw new Exception("Tax ID is required");

		if (dto.getAddress() == null)
			throw new Exception("Address is required");

		// Email Format Validation
		if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new Exception("Invalid Email Format");
		}
	}

	// ============================================================
	// CREATE / UPDATE EXPORTER
	// ============================================================
	@PutMapping("/createUpdateExporter")
	public ResponseEntity<ResponseDTO> createUpdateExporter(@RequestBody ExporterDTO exporterDTO) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			validateExporter(exporterDTO);

			Map<String, Object> serviceResponse = exporterService.createUpdateExporter(exporterDTO);

			responseMap.put(CommonConstant.STRING_MESSAGE, serviceResponse.get("message"));
			responseMap.put("exporterVO", serviceResponse.get("exporterVO"));

			responseDTO = createServiceResponse(responseMap);

		} catch (Exception e) {
			responseDTO = createServiceResponseError(responseMap, e.getMessage(), e.getMessage());
		}

		return ResponseEntity.ok(responseDTO);
	}

	// ============================================================
	// GET EXPORTER BY ID
	// ============================================================
	@GetMapping("/getByIdExporter")
	public ResponseEntity<ResponseDTO> getExporterById(@RequestParam Long id) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;
		String errorMsg = null;
		ExporterVO exporterVO = new ExporterVO();

		try {
			exporterVO = exporterService.getExporterById(id);

		} catch (Exception e) {
			errorMsg = e.getMessage();
		}

		if (StringUtils.isBlank(errorMsg)) {
			responseMap.put(CommonConstant.STRING_MESSAGE, "Exporter information retrieved successfully");
			responseMap.put("exporterVO", exporterVO);
			responseDTO = createServiceResponse(responseMap);
		} else {
			responseDTO = createServiceResponseError(responseMap, "Exporter information fetch failed", errorMsg);
		}

		return ResponseEntity.ok(responseDTO);
	}

	// ============================================================
	// PAGINATION (same format as Payouts)
	// ============================================================
	@GetMapping("/getAllExportersByOrgId")
	public ResponseEntity<ResponseDTO> getExportersByOrgId(
			@RequestParam Long orgId,
			@RequestParam(required = false) String branchCode,
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int count) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> exportersVO =
					exporterService.getExportersByOrgId(orgId, branchCode, search, page, count);

			responseMap.put("message", "Exporters retrieved successfully");
			responseMap.put("exportersVO", exportersVO);

			responseDTO = createServiceResponse(responseMap);

		} catch (Exception e) {
			responseDTO = createServiceResponseError(responseMap, "Error fetching exporters", e.getMessage());
		}

		return ResponseEntity.ok(responseDTO);
	}

	// ============================================================
	// SUCCESS RESPONSE
	// ============================================================
	public ResponseDTO createServiceResponse(Map<String, Object> responseMap) {
		ResponseDTO response = new ResponseDTO();
		response.setStatus(true);
		response.setStatusFlag("Success");
		response.setParamObjectsMap(responseMap);
		return response;
	}

	// ============================================================
	// ERROR RESPONSE
	// ============================================================
	public ResponseDTO createServiceResponseError(Map<String, Object> responseMap, String msg, String errorMsg) {
		ResponseDTO response = new ResponseDTO();
		response.setStatus(false);
		response.setStatusFlag("Error");
		responseMap.put("errorMessage", errorMsg);
		response.setParamObjectsMap(responseMap);
		return response;
	}
}
