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

import com.efit.ganapathi.dto.Form1DTO;
import com.efit.ganapathi.dto.Form6DTO;
import com.efit.ganapathi.dto.Form9DTO;
import com.efit.ganapathi.dto.Form9DetailsDTO;
import com.efit.ganapathi.entity.Form1VO;
import com.efit.ganapathi.entity.Form6VO;
import com.efit.ganapathi.entity.Form9DetailsVO;
import com.efit.ganapathi.entity.Form9VO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.Form1Repo;
import com.efit.ganapathi.repo.Form6Repo;
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

	@Autowired
	Form6Repo form6Repo;

	@Autowired
	Form1Repo form1Repo;

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

	// Form6

	@Override
	public Map<String, Object> getAllForm6ByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("exporter").ascending());
		Page<Form6VO> customerPage = form6Repo.getAllForm6ByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public Form6VO getForm6ById(Long id) {

		return form6Repo.getForm6ById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateForm6(Form6DTO form6DTO) throws ApplicationException {

		Form6VO form6VO = new Form6VO();

		String message;

		if (ObjectUtils.isNotEmpty(form6DTO.getId())) {

			form6VO = form6Repo.findById(form6DTO.getId())
					.orElseThrow(() -> new ApplicationException("Form6 Not Found!"));
			form6VO.setUpdatedBy(form6DTO.getCreatedBy());

			message = "Form6 Updated Successfully";
		} else {

			form6VO.setUpdatedBy(form6DTO.getCreatedBy());
			form6VO.setCreatedBy(form6DTO.getCreatedBy());
			message = "Form6 Created Successfully";
		}

		createUpdateForm6VOByForm6DTO(form6DTO, form6VO);
		form6Repo.save(form6VO);
		Map<String, Object> response = new HashMap<>();
		response.put("form6VO", form6VO);
		response.put("message", message);
		return response;
	}

	private void createUpdateForm6VOByForm6DTO(@Valid Form6DTO form6DTO, Form6VO form6VO) throws ApplicationException {
		form6VO.setExporter(form6DTO.getExporter());
		form6VO.setContactPerson(form6DTO.getContactPerson());
		form6VO.setExporterEmail(form6DTO.getExporterEmail());
		form6VO.setWGenerator(form6DTO.getWGenerator());
		form6VO.setWGeneratorPerson(form6DTO.getWGeneratorPerson());
		form6VO.setGeneratorEmail(form6DTO.getGeneratorEmail());
		form6VO.setGenerationSite(form6DTO.getGenerationSite());
		form6VO.setImporter(form6DTO.getImporter());
		form6VO.setImporterPerson(form6DTO.getImporterPerson());
		form6VO.setImporterEmail(form6DTO.getImporterEmail());
		form6VO.setTrader(form6DTO.getTrader());
		form6VO.setTraderPerson(form6DTO.getTraderPerson());
		form6VO.setTraderEmail(form6DTO.getTraderEmail());
		form6VO.setActualUserDetails(form6DTO.getActualUserDetails());
		form6VO.setRefNo(form6DTO.getRefNo());
		form6VO.setBill(form6DTO.getBill());
		form6VO.setCountryImEx(form6DTO.getCountryImEx());
		form6VO.setDescription(form6DTO.getDescription());
		form6VO.setPhysical(form6DTO.getPhysical());
		form6VO.setQuantity(form6DTO.getQuantity());
		form6VO.setChemical(form6DTO.getChemical());
		form6VO.setBaselNo(form6DTO.getBaselNo());
		form6VO.setUnShippingName(form6DTO.getUnShippingName());
		form6VO.setUnClass(form6DTO.getUnClass());
		form6VO.setUnNo(form6DTO.getUnNo());
		form6VO.setHNumber(form6DTO.getHNumber());
		form6VO.setYNumber(form6DTO.getYNumber());
		form6VO.setItchs(form6DTO.getItchs());
		form6VO.setCustomsCode(form6DTO.getCustomsCode());
		form6VO.setOther(form6DTO.getOther());
		form6VO.setPackages(form6DTO.getPackages());
		form6VO.setNumber(form6DTO.getNumber());
		form6VO.setRequirements(form6DTO.getRequirements());
		form6VO.setMovement(form6DTO.getMovement());
		form6VO.setMultiple(form6DTO.getMultiple());
		form6VO.setExpected(form6DTO.getExpected());
		form6VO.setEstimated(form6DTO.getEstimated());
		form6VO.setTransporter(form6DTO.getTransporter());
		form6VO.setTransporterPerson(form6DTO.getTransporterPerson());
		form6VO.setTransporterEmail(form6DTO.getTransporterEmail());
		form6VO.setRegistrationNo(form6DTO.getRegistrationNo());
		form6VO.setTransportType(form6DTO.getTransportType());
		form6VO.setTransferDate(form6DTO.getTransferDate());
		form6VO.setDeclaration(form6DTO.getDeclaration());
		form6VO.setCompletedImporter(form6DTO.getCompletedImporter());
		form6VO.setShipmentReceived(form6DTO.getShipmentReceived());
		form6VO.setRecovery(form6DTO.getRecovery());
		form6VO.setRCode(form6DTO.getRCode());
		form6VO.setTechnology(form6DTO.getTechnology());
		form6VO.setSpecificConditions(form6DTO.getSpecificConditions());
		form6VO.setCreatedBy(form6DTO.getCreatedBy());
		form6VO.setOrgId(form6DTO.getOrgId());

	}

	// Form1

	@Override
	public Map<String, Object> getAllForm1ByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("production").ascending());
		Page<Form1VO> customerPage = form1Repo.getAllForm1ByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public Form1VO getForm1ById(Long id) {

		return form1Repo.getForm1ById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateForm1(Form1DTO form1DTO) throws ApplicationException {

		Form1VO form1VO = new Form1VO();

		String message;

		if (ObjectUtils.isNotEmpty(form1DTO.getId())) {

			form1VO = form1Repo.findById(form1DTO.getId())
					.orElseThrow(() -> new ApplicationException("Form1 Not Found!"));
			form1VO.setUpdatedBy(form1DTO.getCreatedBy());

			message = "Form1 Updated Successfully";
		} else {

			form1VO.setUpdatedBy(form1DTO.getCreatedBy());
			form1VO.setCreatedBy(form1DTO.getCreatedBy());
			message = "Form1 Created Successfully";
		}

		createUpdateForm1VOByForm1DTO(form1DTO, form1VO);
		form1Repo.save(form1VO);
		Map<String, Object> response = new HashMap<>();
		response.put("form1VO", form1VO);
		response.put("message", message);
		return response;
	}

	private void createUpdateForm1VOByForm1DTO(@Valid Form1DTO form1DTO, Form1VO form1VO) throws ApplicationException {
		form1VO.setGoodsDescription(form1DTO.getGoodsDescription());
		form1VO.setProduction(form1DTO.getProduction());
		form1VO.setOrigin(form1DTO.getOrigin());
		form1VO.setWObtained(form1DTO.getWObtained());
		form1VO.setWExplanation(form1DTO.getWExplanation());
		form1VO.setImportValue(form1DTO.getImportValue());
		form1VO.setHsCode(form1DTO.getHsCode());
		form1VO.setComponent(form1DTO.getComponent());
		form1VO.setManufactured(form1DTO.getManufactured());
		form1VO.setProcured(form1DTO.getProcured());
		form1VO.setConfirmation(form1DTO.getConfirmation());
		form1VO.setMinimisUsed(form1DTO.getMinimisUsed());
		form1VO.setMinimisDescription(form1DTO.getMinimisDescription());
		form1VO.setAccumulationUsed(form1DTO.getAccumulationUsed());
		form1VO.setAccumulationDescription(form1DTO.getAccumulationDescription());
		form1VO.setIMaterialUsed(form1DTO.getIMaterialUsed());
		form1VO.setIMaterialDescription(form1DTO.getIMaterialDescription());
		form1VO.setContent(form1DTO.getContent());
		form1VO.setCtcRuleApplied(form1DTO.getCtcRuleApplied());
		form1VO.setOriginHsCode(form1DTO.getOriginHsCode());
		form1VO.setRuleApplied(form1DTO.getRuleApplied());
		form1VO.setRuleType(form1DTO.getRuleType());
		form1VO.setRetrospectively(form1DTO.getRetrospectively());
		form1VO.setRetrospectiveReason(form1DTO.getRetrospectiveReason());
		form1VO.setDirectlyShipped(form1DTO.getDirectlyShipped());
		form1VO.setDirectShipmentVerified(form1DTO.getDirectShipmentVerified());
		form1VO.setCreatedBy(form1DTO.getCreatedBy());
		form1VO.setOrgId(form1DTO.getOrgId());

	}

}
