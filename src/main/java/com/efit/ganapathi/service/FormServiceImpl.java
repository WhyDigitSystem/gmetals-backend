package com.efit.ganapathi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efit.ganapathi.dto.Form9DTO;
import com.efit.ganapathi.dto.Form9DetailsDTO;
import com.efit.ganapathi.entity.Form9DetailsVO;
import com.efit.ganapathi.entity.Form9VO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.Form9DetailsRepo;
import com.efit.ganapathi.repo.Form9Repo;

@Service
public class FormServiceImpl implements FormService {

	public static final Logger LOGGER = LoggerFactory.getLogger(FormServiceImpl.class);

	@Autowired
	Form9Repo form9Repo;

	@Autowired
	Form9DetailsRepo form9DetailsRepo;

	@Autowired
	PaginationService paginationService;

	// Form

	@Override
	public Map<String, Object> getAllForm9ByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("exporter").ascending());
		Page<Form9VO> customerPage = form9Repo.getAllForm9ByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public Form9VO getForm9ById(Long id) {

		return form9Repo.getForm9ById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateForm9(Form9DTO form9DTO) throws ApplicationException {

		Form9VO form9VO = new Form9VO();

		String message;

		if (ObjectUtils.isNotEmpty(form9DTO.getId())) {

			form9VO = form9Repo.findById(form9DTO.getId())
					.orElseThrow(() -> new ApplicationException("Form9 Not Found!"));
			form9VO.setUpdatedBy(form9DTO.getCreatedBy());

			message = "Form9 Updated Successfully";
		} else {

			form9VO.setUpdatedBy(form9DTO.getCreatedBy());
			form9VO.setCreatedBy(form9DTO.getCreatedBy());
			message = "Form9 Created Successfully";
		}

		createUpdateForm9VOByForm9DTO(form9DTO, form9VO);
		form9Repo.save(form9VO);
		Map<String, Object> response = new HashMap<>();
		response.put("form9VO", form9VO);
		response.put("message", message);
		return response;
	}

	private void createUpdateForm9VOByForm9DTO(@Valid Form9DTO form9DTO, Form9VO form9VO) throws ApplicationException {
		form9VO.setExporter(form9DTO.getExporter());
		form9VO.setContactPerson(form9DTO.getContactPerson());
		form9VO.setTel(form9DTO.getTel());
		form9VO.setFax(form9DTO.getFax());
		form9VO.setWGenerator(form9DTO.getWGenerator());
		form9VO.setWGeneratorPerson(form9DTO.getWGeneratorPerson());
		form9VO.setGenerationSite(form9DTO.getGenerationSite());
		form9VO.setImporter(form9DTO.getImporter());
		form9VO.setRefNo(form9DTO.getRefNo());
		form9VO.setMovementType(form9DTO.getMovementType());
		form9VO.setShipmentNo(form9DTO.getShipmentNo());
		form9VO.setDisposerName(form9DTO.getDisposerName());
		form9VO.setDisposerPerson(form9DTO.getDisposerPerson());
		form9VO.setDisposalSite(form9DTO.getDisposalSite());
		form9VO.setDisposerTelFax(form9DTO.getDisposerTelFax());
		form9VO.setRecoveryMode(form9DTO.getRecoveryMode());
		form9VO.setRCode(form9DTO.getRCode());
		form9VO.setAttachments(form9DTO.getAttachments());
		form9VO.setDesignation(form9DTO.getDesignation());
		form9VO.setPhysical(form9DTO.getPhysical());
		form9VO.setQuantity(form9DTO.getQuantity());
		form9VO.setWasteCode(form9DTO.getWasteCode());
		form9VO.setBaseNo(form9DTO.getBaseNo());
		form9VO.setOecdNo(form9DTO.getOecdNo());
		form9VO.setUnNo(form9DTO.getUnNo());
		form9VO.setItchs(form9DTO.getItchs());
		form9VO.setCustomsCode(form9DTO.getCustomsCode());
		form9VO.setOther(form9DTO.getOther());
		form9VO.setOecdClassification(form9DTO.getOecdClassification());
		form9VO.setPackingType(form9DTO.getPackingType());
		form9VO.setNumber(form9DTO.getNumber());
		form9VO.setUnClassification(form9DTO.getUnClassification());
		form9VO.setUnShippingName(form9DTO.getUnShippingName());
		form9VO.setUnIdentificationNo(form9DTO.getUnIdentificationNo());
		form9VO.setUnClass(form9DTO.getUnClass());
		form9VO.setHNumber(form9DTO.getHNumber());
		form9VO.setYNumber(form9DTO.getYNumber());
		form9VO.setShrequirements(form9DTO.getShrequirements());
		form9VO.setShipmentDate(form9DTO.getShipmentDate());
		form9VO.setReceivedby(form9DTO.getReceivedby());
		form9VO.setCreatedBy(form9DTO.getCreatedBy());
		form9VO.setOrgId(form9DTO.getOrgId());
		if (form9DTO.getId() != null) {
			List<Form9DetailsVO> form9DetailsVO1 = form9DetailsRepo.findByForm9VO(form9VO);
			form9DetailsRepo.deleteAll(form9DetailsVO1);

		}

		List<Form9DetailsVO> form9DetailsVOs = new ArrayList<>();
		for (Form9DetailsDTO form9DetailsDTO : form9DTO.getForm9DetailsDTO()) {
			Form9DetailsVO form9DetailsVO = new Form9DetailsVO();

			form9DetailsVO.setNameAddress(form9DetailsDTO.getNameAddress());
			form9DetailsVO.setRegistrationNo(form9DetailsDTO.getRegistrationNo());
			form9DetailsVO.setTelFax(form9DetailsDTO.getTelFax());
			form9DetailsVO.setTransport(form9DetailsDTO.getTransport());
			form9DetailsVO.setDateoftransfer(form9DetailsDTO.getDateoftransfer());
			form9DetailsVO.setSignature(form9DetailsDTO.getSignature());

			form9DetailsVO.setForm9VO(form9VO);
			form9DetailsVOs.add(form9DetailsVO);
		}

		form9VO.setForm9DetailsVO(form9DetailsVOs);

	}

}
