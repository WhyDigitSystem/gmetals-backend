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
import com.efit.ganapathi.common.UserConstants;
import com.efit.ganapathi.dto.CertificateDTO;
import com.efit.ganapathi.dto.InvoiceDTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.entity.CertificateVO;
import com.efit.ganapathi.entity.InvoiceVO;
import com.efit.ganapathi.service.InvoiceService;

@CrossOrigin
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
	InvoiceService invoiceService;

	// Invoice

	@PutMapping("/updateCreateInvoice")
	public ResponseEntity<ResponseDTO> updateCreateInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		String methodName = "updateCreateInvoice()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> invoiceVO = invoiceService.updateCreateInvoice(invoiceDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, invoiceVO.get("message"));
			responseObjectsMap.put("invoiceVO", invoiceVO.get("invoiceVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllInvoiceByOrgId")
	public ResponseEntity<ResponseDTO> getAllInvoiceByOrgId(@RequestParam Long orgId,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		String methodName = "getAllInvoiceByOrgId()";
		LOGGER.debug("Starting {}", methodName);

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> orderBookingVO = invoiceService.getAllInvoiceByOrgId(orgId, search, page, size);
			responseMap.put("message", "Invoice retrieved successfully");
			responseMap.put("rewardPolicyVO", orderBookingVO);
			responseDTO = createServiceResponse(responseMap);
		} catch (Exception e) {
			LOGGER.error("Error in {}: {}", methodName, e.getMessage());
			responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
		}

		LOGGER.debug("Ending {}", methodName);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/getInvoiceById")
	public ResponseEntity<ResponseDTO> getInvoiceById(@RequestParam Long id) {
		String methodName = "getInvoiceById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		InvoiceVO invoiceVO = new InvoiceVO();
		try {
			invoiceVO = invoiceService.getInvoiceById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Invoice get successfully By id");
			responseObjectsMap.put("invoiceVO", invoiceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"OrderBooking information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Certificate

	@PutMapping("/updateCreateCertificate")
	public ResponseEntity<ResponseDTO> updateCreateCertificate(@RequestBody CertificateDTO certificateDTO) {
		String methodName = "updateCreateCertificate()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> certificateVO = invoiceService.updateCreateCertificate(certificateDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, certificateVO.get("message"));
			responseObjectsMap.put("certificateVO", certificateVO.get("certificateVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllCertificateByOrgId")
	public ResponseEntity<ResponseDTO> getAllCertificateByOrgId(@RequestParam Long orgId,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		String methodName = "getAllCertificateByOrgId()";
		LOGGER.debug("Starting {}", methodName);

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> certificateVO = invoiceService.getAllCertificateByOrgId(orgId, search, page, size);
			responseMap.put("message", "Certificate retrieved successfully");
			responseMap.put("certificateVO", certificateVO);
			responseDTO = createServiceResponse(responseMap);
		} catch (Exception e) {
			LOGGER.error("Error in {}: {}", methodName, e.getMessage());
			responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
		}

		LOGGER.debug("Ending {}", methodName);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/getCertificateById")
	public ResponseEntity<ResponseDTO> getCertificateById(@RequestParam Long id) {
		String methodName = "getCertificateById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		CertificateVO certificateVO = new CertificateVO();
		try {
			certificateVO = invoiceService.getCertificateById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Certificate get successfully By id");
			responseObjectsMap.put("certificateVO", certificateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Certificate information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}
