package com.efit.ganapathi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.efit.ganapathi.dto.EnquiryDTO;
import com.efit.ganapathi.dto.PackingListDTO;
import com.efit.ganapathi.dto.PackingListDetailsDTO;
import com.efit.ganapathi.entity.EnquiryVO;
import com.efit.ganapathi.entity.PackingListDetailsVO;
import com.efit.ganapathi.entity.PackingListVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.EnquiryRepo;
import com.efit.ganapathi.repo.PackingListDetailsRepo;
import com.efit.ganapathi.repo.PackingListRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	EnquiryRepo enquiryRepo;

	@Autowired
	PaginationService paginationService;

	@Autowired
	PackingListRepo packingListRepo;

	@Autowired
	PackingListDetailsRepo packingListDetailsRepo;

	// Enquiry

	@Override
	public Map<String, Object> getAllEnquiryByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("cname").ascending());
		Page<EnquiryVO> customerPage = enquiryRepo.getAllEnquiryByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public EnquiryVO getEnquiryById(Long id) {

		return enquiryRepo.getEnquiryById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateEnquiry(@Valid EnquiryDTO enquiryDTO) throws ApplicationException {

		EnquiryVO enquiryVO = new EnquiryVO();

		String message;

		if (ObjectUtils.isNotEmpty(enquiryDTO.getId())) {

			enquiryVO = enquiryRepo.findById(enquiryDTO.getId())
					.orElseThrow(() -> new ApplicationException("Enquiry Not Found!"));
			enquiryVO.setUpdatedBy(enquiryDTO.getCreatedBy());

			if (!enquiryVO.getCustomerId().equalsIgnoreCase(enquiryDTO.getCustomerId())) {
				if (enquiryRepo.existsByCustomerIdAndOrgId(enquiryDTO.getBranch(), enquiryDTO.getOrgId())) {
					String errorMessage = String.format("This CustomerId: %s Already Exists in This Organization",
							enquiryDTO.getBranch());
					throw new ApplicationException(errorMessage);
				}
				enquiryVO.setCustomerId(enquiryDTO.getBranch().toUpperCase());
			}

			message = "Enquiry Updated Successfully";
		} else {

			if (enquiryRepo.existsByCustomerIdAndOrgId(enquiryDTO.getCustomerId(), enquiryDTO.getOrgId())) {
				String errorMessage = String.format("This CustomerId: %s Already Exists in This Organization",
						enquiryDTO.getCustomerId());
				throw new ApplicationException(errorMessage);
			}

			int count = enquiryRepo.getMaxEnquiryId();

			if (count == 0) {
				count = 1000000000;
			}

			count = count + 1;
			enquiryVO.setEnquiryId(count);

			enquiryVO.setUpdatedBy(enquiryDTO.getCreatedBy());
			enquiryVO.setCreatedBy(enquiryDTO.getCreatedBy());

			message = "Enquiry Created Successfully";
		}

		createEnquiryVOByEnquiryDTO(enquiryDTO, enquiryVO);
		enquiryRepo.save(enquiryVO);
		Map<String, Object> response = new HashMap<>();
		response.put("enquiryVO", enquiryVO);
		response.put("message", message);
		return response;
	}

	private void createEnquiryVOByEnquiryDTO(@Valid EnquiryDTO enquiryDTO, EnquiryVO enquiryVO)
			throws ApplicationException {
		enquiryVO.setCustomerName(enquiryDTO.getCustomerName());
		enquiryVO.setBranchCode(enquiryDTO.getBranchCode());
		enquiryVO.setCustomerId(enquiryDTO.getCustomerId());
		enquiryVO.setCustomerConcatNumbers(enquiryDTO.getCustomerConcatNumbers());
		enquiryVO.setCustomerEmailPhone(enquiryDTO.getCustomerEmailPhone());
		enquiryVO.setCustomerAddress(enquiryDTO.getCustomerAddress());
		enquiryVO.setEnquiryType(enquiryDTO.getEnquiryType());
		enquiryVO.setOrgId(enquiryDTO.getOrgId());
		enquiryVO.setStatus(enquiryDTO.getStatus());
		enquiryVO.setCustomerStatus(enquiryDTO.getCustomerStatus());
		enquiryVO.setFromRange(enquiryDTO.getFromRange());
		enquiryVO.setToRange(enquiryDTO.getToRange());
		enquiryVO.setEType(enquiryDTO.getEType());
		enquiryVO.setSource(enquiryDTO.getSource());
		enquiryVO.setSubject(enquiryDTO.getSubject());
		enquiryVO.setDescription(enquiryDTO.getDescription());

		enquiryVO.setPriority(enquiryDTO.getPriority());
		enquiryVO.setEnquiryStatus(enquiryDTO.getEnquiryStatus());
		enquiryVO.setAssignedAgent(enquiryDTO.getAssignedAgent());
		enquiryVO.setSlaHours(enquiryDTO.getSlaHours());
		enquiryVO.setCustomerSentiment(enquiryDTO.getCustomerSentiment());
		enquiryVO.setAttachmentsCount(enquiryDTO.getAttachmentsCount());
		enquiryVO.setAddInteractionNote(enquiryDTO.getAddInteractionNote());
		enquiryVO.setInternalNotes(enquiryDTO.getInternalNotes());
		enquiryVO.setCustomerId(enquiryDTO.getCustomerId());
		enquiryVO.setBranch(enquiryDTO.getBranch());

	}

	@Override
	public List<Map<String, Object>> getCustomerNameAndCode(Long orgId) {
		Set<Object[]> currency = enquiryRepo.getCustomerNameAndCode(orgId);
		return getCustomerNameAndCode(currency);
	}

	private List<Map<String, Object>> getCustomerNameAndCode(Set<Object[]> currency) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : currency) {
			Map<String, Object> map = new HashMap<>();
			map.put("partyName", ch[0] != null ? ch[0].toString() : "");
			map.put("partyCode", ch[1] != null ? ch[1].toString() : "");

			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getAssignedAgent(Long orgId) {
		Set<Object[]> currency = enquiryRepo.getAssignedAgent(orgId);
		return getAssignedAgent(currency);
	}

	private List<Map<String, Object>> getAssignedAgent(Set<Object[]> currency) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : currency) {
			Map<String, Object> map = new HashMap<>();
			map.put("userName", ch[0] != null ? ch[0].toString() : "");

			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getEnquiryCount(Long orgId, String branchCode, String Type) {
		Set<Object[]> result = enquiryRepo.getEnquiryCount(orgId, branchCode, Type);
		return getEnquiryCount(result);
	}

	private List<Map<String, Object>> getEnquiryCount(Set<Object[]> result) {
		List<Map<String, Object>> details1 = new ArrayList<>();
		for (Object[] fs : result) {
			Map<String, Object> part = new HashMap<>();
			part.put("totalEnquiries", fs[0] != null ? new BigDecimal(fs[0].toString()) : BigDecimal.ZERO);
			part.put("open", fs[1] != null ? new BigDecimal(fs[1].toString()) : BigDecimal.ZERO);
			part.put("inProgress", fs[2] != null ? new BigDecimal(fs[2].toString()) : BigDecimal.ZERO);
			part.put("closed", fs[3] != null ? new BigDecimal(fs[3].toString()) : BigDecimal.ZERO);
			details1.add(part);
		}
		return details1;

	}

	// PackingList

	@Override
	public Map<String, Object> getAllPackingListByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("packinglistnumber").ascending());
		Page<PackingListVO> customerPage = packingListRepo.getAllPackingListByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public PackingListVO getPackingListById(Long id) {

		return packingListRepo.getPackingListById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreatePackingList(PackingListDTO packingListDTO) throws ApplicationException {

		PackingListVO packingListVO = new PackingListVO();

		String message;

		if (ObjectUtils.isNotEmpty(packingListDTO.getId())) {

			packingListVO = packingListRepo.findById(packingListDTO.getId())
					.orElseThrow(() -> new ApplicationException("PackingList Not Found!"));
			packingListVO.setUpdatedBy(packingListDTO.getCreatedBy());

			if (!packingListVO.getPackingListNumber().equalsIgnoreCase(packingListDTO.getPackingListNumber())) {
				if (packingListRepo.existsByPackingListNumberAndOrgId(packingListDTO.getPackingListNumber(),
						packingListDTO.getOrgId())) {
					String errorMessage = String.format(
							"This PackingListNumber: %s Already Exists in This Organization",
							packingListDTO.getPackingListNumber());
					throw new ApplicationException(errorMessage);
				}
				packingListVO.setPackingListNumber(packingListDTO.getPackingListNumber().toUpperCase());
			}

			message = "PackingList Updated Successfully";
		} else {

			if (packingListRepo.existsByPackingListNumberAndOrgId(packingListDTO.getPackingListNumber(),
					packingListDTO.getOrgId())) {
				String errorMessage = String.format("This PackingListNumber: %s Already Exists in This Organization",
						packingListDTO.getPackingListNumber());
				throw new ApplicationException(errorMessage);
			}

			packingListVO.setUpdatedBy(packingListDTO.getCreatedBy());
			packingListVO.setCreatedBy(packingListDTO.getCreatedBy());
			message = "OrderBooking Created Successfully";
		}

		createUpdatePackingListVOByPackingListDTO(packingListDTO, packingListVO);
		packingListRepo.save(packingListVO);
		Map<String, Object> response = new HashMap<>();
		response.put("packingListVO", packingListVO);
		response.put("message", message);
		return response;
	}

	private void createUpdatePackingListVOByPackingListDTO(@Valid PackingListDTO packingListDTO,
			PackingListVO packingListVO) throws ApplicationException {
		packingListVO.setImporter(packingListDTO.getImporter());
		packingListVO.setBranchCode(packingListDTO.getBranchCode());
		packingListVO.setExporter(packingListDTO.getExporter());
		packingListVO.setPackingListNumber(packingListDTO.getPackingListNumber());
		packingListVO.setCreatedBy(packingListDTO.getCreatedBy());
		packingListVO.setPackingDate(packingListDTO.getPackingDate());
		packingListVO.setPackingType(packingListDTO.getPackingType());
		packingListVO.setOrgId(packingListDTO.getOrgId());

		if (packingListDTO.getId() != null) {
			List<PackingListDetailsVO> packingListDetailsVO1 = packingListDetailsRepo
					.findByPackingListVO(packingListVO);
			packingListDetailsRepo.deleteAll(packingListDetailsVO1);

		}

		BigDecimal totalWeight = BigDecimal.ZERO;
		double totalBoxes = 0.0;
		List<PackingListDetailsVO> packingListDetailsVOs = new ArrayList<>();
		for (PackingListDetailsDTO packingListDetailsDTO : packingListDTO.getPackingListDetailsDTO()) {
			PackingListDetailsVO packingListDetailsVO = new PackingListDetailsVO();

			packingListDetailsVO.setItemName(packingListDetailsDTO.getItemName());
			packingListDetailsVO.setItemCode(packingListDetailsDTO.getItemCode());
			packingListDetailsVO.setItemDescription(packingListDetailsDTO.getItemDescription());
			packingListDetailsVO.setWeight(packingListDetailsDTO.getWeight());
			totalWeight = totalWeight.add(packingListDetailsVO.getWeight());
			packingListDetailsVO.setBox(packingListDetailsDTO.getBox());
			totalBoxes = totalBoxes + packingListDetailsVO.getBox();
			packingListDetailsVO.setPackingListVO(packingListVO);
			packingListDetailsVOs.add(packingListDetailsVO);
		}

		packingListVO.setTotalBoxes(totalBoxes);
		packingListVO.setTotalWeight(totalWeight);
		packingListVO.setPackingListDetailsVO(packingListDetailsVOs);

	}

}
