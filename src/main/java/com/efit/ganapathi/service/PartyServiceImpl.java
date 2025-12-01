package com.efit.ganapathi.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;     // ✅ Correct import
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.PartyDTO;
import com.efit.ganapathi.entity.PartyVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.PartyRepo;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepo partyRepo;

    @Override
    public Map<String, Object> createUpdateParty(PartyDTO partyDTO) throws ApplicationException {

        PartyVO partyVO = new PartyVO();
        String message;

        if (ObjectUtils.isEmpty(partyDTO.getId())) {

            if (partyRepo.existsByPartyCodeAndOrgId(
                    partyDTO.getPartyCode(), partyDTO.getOrgId())) {
                throw new ApplicationException("Party Code already exists");
            }

            partyVO.setCreatedBy(partyDTO.getCreatedBy());
            partyVO.setUpdatedBy(partyDTO.getCreatedBy());

            mapPartyDtoToPartyVo(partyDTO, partyVO);
            message = "Party Created Successfully";

        } else {

            partyVO = partyRepo.findById(partyDTO.getId())
                    .orElseThrow(() -> new ApplicationException("Party not found"));

            if (!partyVO.getPartyCode().equalsIgnoreCase(partyDTO.getPartyCode())) {
                if (partyRepo.existsByPartyCodeAndOrgId(
                        partyDTO.getPartyCode(), partyDTO.getOrgId())) {
                    throw new ApplicationException("Party Code already exists");
                }
                partyVO.setPartyCode(partyDTO.getPartyCode());
            }

            partyVO.setUpdatedBy(partyDTO.getCreatedBy());
            mapPartyDtoToPartyVo(partyDTO, partyVO);

            message = "Party Updated Successfully";
        }

        partyRepo.save(partyVO);

        Map<String, Object> response = new HashMap<>();
        response.put("partyVO", partyVO);
        response.put("message", message);

        return response;
    }

    private void mapPartyDtoToPartyVo(PartyDTO dto, PartyVO vo) {

        vo.setPartyName(dto.getPartyName());
        vo.setPartyCode(dto.getPartyCode());
        vo.setPartyType(dto.getPartyType());
        vo.setContactPerson(dto.getContactPerson());
        vo.setPhone(dto.getPhone());
        vo.setEmail(dto.getEmail());
        vo.setAddress(dto.getAddress());
        vo.setCreditLimit(dto.getCreditLimit());

        vo.setBranch(dto.getBranch());
        vo.setBranchCode(dto.getBranchCode());
        vo.setActive(dto.isActive());
        vo.setOrgId(dto.getOrgId());
    }

    @Override
    public PartyVO getRolesById(Long id) {
        return partyRepo.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> getAllParty(Long orgId, String branchCode, String search, int page, int count) {

        if (search != null) {
            search = search.trim();
            if (search.isEmpty()) search = null;
        }

        Pageable pageable = PageRequest.of(page - 1, count, Sort.by("partyName").ascending());  // ✅ Correct pageable

        Page<PartyVO> partyPage = partyRepo.getPartyByFilters(orgId, branchCode, search, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Parties fetched successfully");
        response.put("totalItems", partyPage.getTotalElements());
        response.put("totalPages", partyPage.getTotalPages());
        response.put("currentPage", page);
        response.put("partyList", partyPage.getContent());

        return response;
    }
}
