package com.efit.ganapathi.service;

import java.util.Map;

import com.efit.ganapathi.dto.ExporterDTO;
import com.efit.ganapathi.entity.ExporterVO;
import com.efit.ganapathi.exception.ApplicationException;

public interface ExporterService {

	Map<String, Object> createUpdateExporter(ExporterDTO exporterDTO) throws ApplicationException;

	ExporterVO getExporterById(Long id);

	Map<String, Object> getExportersByOrgId(Long orgId, String branchCode, String search, int page, int count);

}
