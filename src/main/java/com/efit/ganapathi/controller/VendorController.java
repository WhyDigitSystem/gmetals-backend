package com.efit.ganapathi.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efit.ganapathi.common.CommonConstant;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.dto.VendorDTO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.security.VendorService;


@CrossOrigin
@RestController
@RequestMapping("/api/vendor")
public class VendorController extends BaseController {

	@Autowired
	VendorService vendorService;

	public static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

	
	@PutMapping("/createUpdateVendor")
	public ResponseEntity<ResponseDTO> createUpdateVendor(@RequestBody VendorDTO vendorDTO)
	        throws ApplicationException {

	    String methodName = "createUpdateVendor()";
	    LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);

	    ResponseDTO responseDTO;
	    Map<String, Object> responseObjectsMap = new HashMap<>();

	    try {
	        // Call service
	        Map<String, Object> vendorResponse = vendorService.createUpdateVendor(vendorDTO);

	        // Extract actual vendorVO object & message
	        Object vendorVO = vendorResponse.get("vendorVO");
	        String message = (String) vendorResponse.get("message");

	        // Prepare response
	        responseObjectsMap.put("vendorVO", vendorVO);
	        responseObjectsMap.put("message", message);

	        // Final response wrapper
	        responseDTO = createServiceResponse(responseObjectsMap);

	    } catch (Exception e) {
	        LOGGER.error("{} - Unexpected Error: {}", methodName, e.getMessage(), e);
	        responseDTO = createServiceResponseError(responseObjectsMap, "Unexpected Error", "Something went wrong.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
	    }

	    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	    return ResponseEntity.ok(responseDTO);
	}
	
	
	@GetMapping("/getVendorByOrgId")
	public ResponseEntity<ResponseDTO> getUsersByOrgId(
	        @RequestParam Long orgId,
	        @RequestParam(required = false) String branchCode,
	        @RequestParam(defaultValue = "") String search,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int count
	) {
	    String methodName = "getVendorByOrgId()";
	    LOGGER.debug("Starting {}", methodName);

	    Map<String, Object> responseMap = new HashMap<>();
	    ResponseDTO responseDTO;

	    try {
	        Map<String, Object> data = vendorService.getVendorByOrgId(orgId, branchCode, search, page, count);
	        responseMap.put("message", "Vendor retrieved successfully");
	        responseMap.put("data", data);
	        responseDTO = createServiceResponse(responseMap);
	    } catch (Exception e) {
	        LOGGER.error("Error in {}: {}", methodName, e.getMessage());
	        responseDTO = createServiceResponseError(responseMap, "Error fetching users", e.getMessage());
	    }

	    LOGGER.debug("Ending {}", methodName);
	    return ResponseEntity.ok(responseDTO);
	}
	

}
