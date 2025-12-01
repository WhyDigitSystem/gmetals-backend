package com.efit.ganapathi.controller;



import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;   // ✅ Correct Import
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efit.ganapathi.common.CommonConstant;
import com.efit.ganapathi.common.UserConstants;
import com.efit.ganapathi.dto.PartyDTO;
import com.efit.ganapathi.dto.ResponseDTO;
import com.efit.ganapathi.entity.PartyVO;
import com.efit.ganapathi.service.PartyService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/api/party")
public class PartyController extends BaseController {

    @Autowired
    private PartyService partyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyController.class);  // ✅ Corrected logger

    
    // =================== API ===================
    @PutMapping("/CreateUpdateParty")
    public ResponseEntity<ResponseDTO> createUpdateParty(@Valid @RequestBody PartyDTO partyDTO) {

        String methodName = "createUpdateParty()";
        LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);

        Map<String, Object> responseObjectsMap = new HashMap<>();
        ResponseDTO responseDTO = null;
        String errorMsg = null;

        try {
            // Validate input
//            createUpdateParty(partyDTO);

            // Service call → returns Map
            Map<String, Object> createdPartyVO = partyService.createUpdateParty(partyDTO);

            // Prepare response map
            responseObjectsMap.put(CommonConstant.STRING_MESSAGE, createdPartyVO.get("message"));
            responseObjectsMap.put("partyVO", createdPartyVO.get("partyVO"));

            // Success ResponseDTO
            responseDTO = createServiceResponse(responseObjectsMap);

        } catch (Exception e) {

            errorMsg = e.getMessage();
            LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);

            // Error ResponseDTO
            responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
        }

        LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);

        return ResponseEntity.ok().body(responseDTO);
    }
    
    @GetMapping("/getPartyById")
    public ResponseEntity<ResponseDTO> getRolesById(@RequestParam Long id) {

        String methodName = "getPartyById()";
        LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);

        Map<String, Object> responseObjectsMap = new HashMap<>();
        ResponseDTO responseDTO;
        String errorMsg = null;
        PartyVO partyVO = null;

        try {
            // Service call
            partyVO = partyService.getRolesById(id);

        } catch (Exception e) {
            errorMsg = e.getMessage();
            LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
        }

        // SUCCESS RESPONSE
        if (StringUtils.isEmpty(errorMsg)) {

            responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Party found by ID");
            responseObjectsMap.put("partyVO", partyVO);
            responseDTO = createServiceResponse(responseObjectsMap);

        } else {

            // ERROR RESPONSE
            String fullMsg = "Party not found for ID: " + id;
            responseDTO = createServiceResponseError(responseObjectsMap, "Party not found", fullMsg);

        }

        LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/GetAllParty")
    public ResponseEntity<ResponseDTO> getAllParty(
            @RequestParam Long orgId,
            @RequestParam(required = false) String branchCode,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int count) {

        String methodName = "getAllParty()";
        LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);

        Map<String, Object> responseObjectsMap = new HashMap<>();
        ResponseDTO responseDTO;
        String errorMsg = null;

        try {
            // Service call → must return paginated list
            Map<String, Object> allParties =
                    partyService.getAllParty(orgId, branchCode, search, page, count);

            // Prepare response
            responseObjectsMap.put(CommonConstant.STRING_MESSAGE, allParties.get("message"));
            responseObjectsMap.put("partyList", allParties.get("partyList"));
            responseObjectsMap.put("totalPages", allParties.get("totalPages"));
            responseObjectsMap.put("totalItems", allParties.get("totalItems"));
            responseObjectsMap.put("currentPage", allParties.get("currentPage"));

            // SUCCESS
            responseDTO = createServiceResponse(responseObjectsMap);

        } catch (Exception e) {

            errorMsg = e.getMessage();
            LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);

            // ERROR RESPONSE
            responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
        }

        LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);

        return ResponseEntity.ok().body(responseDTO);
    }


	

}
