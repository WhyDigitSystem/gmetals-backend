package com.efit.ganapathi.security;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.VendorDTO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface VendorService {

	Map<String, Object> createUpdateVendor(VendorDTO vendorDTO) throws ApplicationException;

	Map<String, Object> getVendorByOrgId(Long orgId, String branchCode, String search, int page, int count);

}
