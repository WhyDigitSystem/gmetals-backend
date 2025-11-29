package com.efit.ganapathi.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.efit.ganapathi.common.CommonConstant;
import com.efit.ganapathi.common.UserConstants;
import com.efit.ganapathi.dto.OrderBookingDTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.entity.OrderBookingVO;
import com.efit.ganapathi.service.OrderBookingService;

@CrossOrigin
@RestController
@RequestMapping("/api/orderbooking")
public class OrderBookingController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(OrderBookingController.class);

	@Autowired
	OrderBookingService orderBookingService;

	// OrderBooking

	@PutMapping(value = "/updateCreateOrderBooking", consumes = "multipart/form-data")
	public ResponseEntity<ResponseDTO> updateCreateOrderBooking(
			@RequestPart("orderBookingDTO") OrderBookingDTO orderBookingDTO,
//			@RequestBody OrderBookingDTO orderBookingDTO,
			@RequestPart(value = "invoiceFile", required = false) MultipartFile invoiceFile) {

		String methodName = "updateCreateOrderBooking()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);

		try {
			Map<String, Object> Response = orderBookingService.updateCreateOrderBooking(orderBookingDTO, invoiceFile);

			ResponseDTO responseDTO = createServiceResponse(Response);
			return ResponseEntity.ok(responseDTO);

		} catch (Exception e) {
			LOGGER.error("{} - Unexpected Error: {}", methodName, e.getMessage(), e);
			ResponseDTO responseDTO = createServiceResponseError(new HashMap<>(), "Unexpected Error", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

	@GetMapping("/getAllOrderBookingByOrgId")
	public ResponseEntity<ResponseDTO> getAllOrderBookingByOrgId(@RequestParam Long orgId,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		String methodName = "getAllOrderBookingByOrgId()";
		LOGGER.debug("Starting {}", methodName);

		Map<String, Object> responseMap = new HashMap<>();
		ResponseDTO responseDTO;

		try {
			Map<String, Object> orderBookingVO = orderBookingService.getAllOrderBookingByOrgId(orgId, search, page,
					size);
			responseMap.put("message", "OrderBooking retrieved successfully");
			responseMap.put("rewardPolicyVO", orderBookingVO);
			responseDTO = createServiceResponse(responseMap);
		} catch (Exception e) {
			LOGGER.error("Error in {}: {}", methodName, e.getMessage());
			responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
		}

		LOGGER.debug("Ending {}", methodName);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/getOrderBookingById")
	public ResponseEntity<ResponseDTO> getOrderBookingById(@RequestParam Long id) {
		String methodName = "getOrderBookingById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		OrderBookingVO orderBookingVO = new OrderBookingVO();
		try {
			orderBookingVO = orderBookingService.getOrderBookingById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "OrderBooking get successfully By id");
			responseObjectsMap.put("orderBookingVO", orderBookingVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"OrderBooking information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	@PostMapping("/uploaAttachmentsInBloob")
//	public ResponseEntity<ResponseDTO> uploaAttachmentsInBloob(@RequestParam("file") MultipartFile file,
//			@RequestParam Long id) {
//		String methodName = "uploaAttachmentsInBloob()";
//		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
//		String errorMsg = null;
//		Map<String, Object> responseObjectsMap = new HashMap<>();
//		ResponseDTO responseDTO = null;
//		OrderBookingVO orderBookingVO = null;
//		try {
//			orderBookingVO = orderBookingService.uploaAttachmentsInBloob(file, id);
//		} catch (Exception e) {
//			errorMsg = e.getMessage();
//			LOGGER.error("Unable To Upload PartImage", methodName, errorMsg);
//		}
//		if (StringUtils.isBlank(errorMsg)) {
//			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Attachments Successfully Upload");
//			responseObjectsMap.put("orderBookingVO", orderBookingVO);
//			responseDTO = createServiceResponse(responseObjectsMap);
//		} else {
//			responseDTO = createServiceResponseError(responseObjectsMap, "PhotoBefore Upload Failed", errorMsg);
//		}
//		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
//		return ResponseEntity.ok().body(responseDTO);
//	}

}
