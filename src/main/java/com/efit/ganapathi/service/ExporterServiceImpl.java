package com.efit.ganapathi.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.ExporterDTO;
import com.efit.ganapathi.entity.ExporterVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.ExporterRepo;

@Service
public class ExporterServiceImpl implements ExporterService {

    @Autowired
    private ExporterRepo exporterRepo;

    @Autowired
    private PaginationService paginationService;

    // ************************************
    // VALIDATION
    // ************************************
    private void validateExporterDTO(ExporterDTO dto) throws ApplicationException {

        // Convert empty → null
        if (dto.getCompanyName() != null && dto.getCompanyName().trim().isEmpty()) dto.setCompanyName(null);
        if (dto.getShortName() != null && dto.getShortName().trim().isEmpty()) dto.setShortName(null);
        if (dto.getContactPerson() != null && dto.getContactPerson().trim().isEmpty()) dto.setContactPerson(null);
        if (dto.getEmail() != null && dto.getEmail().trim().isEmpty()) dto.setEmail(null);
        if (dto.getPhone() != null && dto.getPhone().trim().isEmpty()) dto.setPhone(null);
        if (dto.getTaxId() != null && dto.getTaxId().trim().isEmpty()) dto.setTaxId(null);
        if (dto.getAddress() != null && dto.getAddress().trim().isEmpty()) dto.setAddress(null);

        // -------- Required fields --------
        if (dto.getCompanyName() == null)
            throw new ApplicationException("Company Name cannot be empty");

        if (dto.getEmail() == null)
            throw new ApplicationException("Email cannot be empty");

        if (dto.getPhone() == null)
            throw new ApplicationException("Phone cannot be empty");

        if (dto.getTaxId() == null)
            throw new ApplicationException("Tax ID cannot be empty");

        if (dto.getAddress() == null)
            throw new ApplicationException("Address cannot be empty");

        // -------- Email validation --------
        if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ApplicationException("Invalid email address");
        }
    }

    // ************************************
    // CREATE / UPDATE
    // ************************************
    @Override
    public Map<String, Object> createUpdateExporter(ExporterDTO exporterDTO) throws ApplicationException {

        validateExporterDTO(exporterDTO);

        ExporterVO exporterVO = new ExporterVO();
        String message;

        if (ObjectUtils.isEmpty(exporterDTO.getId())) {

            // CREATE: Check unique name
            if (exporterRepo.existsByCompanyNameAndOrgId(
                    exporterDTO.getCompanyName(),
                    exporterDTO.getOrgId()
            )) {
                throw new ApplicationException("Exporter Name already exists");
            }

            exporterVO.setCreatedBy(exporterDTO.getCreatedBy());
            exporterVO.setUpdatedBy(exporterDTO.getCreatedBy());

            mapExporterDtoToExporterVo(exporterDTO, exporterVO);
            message = "Exporter Created successfully";

        } else {

            // UPDATE
            exporterVO = exporterRepo.findById(exporterDTO.getId())
                    .orElseThrow(() -> new ApplicationException("Exporter not found"));

            // Check unique name change
            if (!exporterVO.getCompanyName().equalsIgnoreCase(exporterDTO.getCompanyName())) {
                if (exporterRepo.existsByCompanyNameAndOrgId(
                        exporterDTO.getCompanyName(),
                        exporterDTO.getOrgId()
                )) {
                    throw new ApplicationException("Exporter Name already exists");
                }
                exporterVO.setCompanyName(exporterDTO.getCompanyName());
            }

            exporterVO.setUpdatedBy(exporterDTO.getCreatedBy());
            mapExporterDtoToExporterVo(exporterDTO, exporterVO);

            message = "Exporter Updated successfully";
        }

        exporterRepo.save(exporterVO);

        Map<String, Object> response = new HashMap<>();
        response.put("exporterVO", exporterVO);
        response.put("message", message);

        return response;
    }

    // ************************************
    // DTO → VO MAPPING
    // ************************************
    private void mapExporterDtoToExporterVo(ExporterDTO dto, ExporterVO vo) {

        vo.setCompanyName(dto.getCompanyName());
        vo.setShortName(dto.getShortName());
        vo.setContactPerson(dto.getContactPerson());
        vo.setTaxId(dto.getTaxId());
        vo.setAddress(dto.getAddress());
        vo.setPhone(dto.getPhone());
        vo.setEmail(dto.getEmail());
        vo.setStatus(dto.getStatus());

        vo.setBranch(dto.getBranch());
        vo.setBranchCode(dto.getBranchCode());
        vo.setActive(dto.isActive());
        vo.setOrgId(dto.getOrgId());
    }

    // ************************************
    // GET BY ID
    // ************************************
    @Override
    public ExporterVO getExporterById(Long id) {
        return exporterRepo.findById(id)
                .orElseThrow(() -> new ApplicationContextException("Exporter not found for Id: " + id));
    }

    // ************************************
    // PAGINATION
    // ************************************
    @Override
    public Map<String, Object> getExportersByOrgId(Long orgId, String branchCode, String search, int page, int count) {

        if (search != null) {
            search = search.trim();
            if (search.isEmpty()) search = null;
        }

        Pageable pageable = PageRequest.of(page - 1, count, Sort.by("companyName").ascending());

        Page<ExporterVO> exporterPage =
                exporterRepo.getExportersByFilters(orgId, branchCode, search, pageable);

        return paginationService.buildResponse(exporterPage);
    }
}
