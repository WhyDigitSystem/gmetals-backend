package com.efit.ganapathi.service;


import java.util.Map;

import com.efit.ganapathi.dto.PartyDTO;
import com.efit.ganapathi.entity.PartyVO;
import com.efit.ganapathi.exception.ApplicationException;

public interface PartyService {

    Map<String, Object> createUpdateParty(PartyDTO partyDTO) throws ApplicationException;

	PartyVO getRolesById(Long id);
	
	Map<String, Object> getAllParty(Long orgId, String branchCode, String search, int page, int count);


	
}
