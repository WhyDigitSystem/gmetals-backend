package com.efit.ganapathi.service;

import java.math.BigDecimal;
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

import com.efit.ganapathi.dto.CertificateDTO;
import com.efit.ganapathi.dto.CertificateDetailsDTO;
import com.efit.ganapathi.dto.InvoiceDTO;
import com.efit.ganapathi.dto.InvoiceDetailsDTO;
import com.efit.ganapathi.entity.CertificateDetailsVO;
import com.efit.ganapathi.entity.CertificateVO;
import com.efit.ganapathi.entity.InvoiceDetailsVO;
import com.efit.ganapathi.entity.InvoiceVO;
import com.efit.ganapathi.exception.ApplicationException;
import com.efit.ganapathi.repo.CertificateDetailsRepo;
import com.efit.ganapathi.repo.CertificateRepo;
import com.efit.ganapathi.repo.InvoiceDetailsRepo;
import com.efit.ganapathi.repo.InvoiceRepo;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	public static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

	@Autowired
	InvoiceRepo invoiceRepo;

	@Autowired
	InvoiceDetailsRepo invoiceDetailsRepo;

	@Autowired
	PaginationService paginationService;

	@Autowired
	AmountInWordsConverterService amountInWordsConverterService;

	@Autowired
	CertificateRepo certificateRepo;

	@Autowired
	CertificateDetailsRepo certificateDetailsRepo;

	// Invoice

	@Override
	public Map<String, Object> getAllInvoiceByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("invoiceno").ascending());
		Page<InvoiceVO> customerPage = invoiceRepo.getAllInvoiceByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public InvoiceVO getInvoiceById(Long id) {

		return invoiceRepo.getInvoiceById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateInvoice(InvoiceDTO invoiceDTO) throws ApplicationException {

		InvoiceVO invoiceVO = new InvoiceVO();

		String message;

		if (ObjectUtils.isNotEmpty(invoiceDTO.getId())) {

			invoiceVO = invoiceRepo.findById(invoiceDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invoice Not Found!"));
			invoiceVO.setUpdatedBy(invoiceDTO.getCreatedBy());

			if (!invoiceVO.getInvoiceNo().equalsIgnoreCase(invoiceDTO.getInvoiceNo())) {
				if (invoiceRepo.existsByInvoiceNoAndOrgId(invoiceDTO.getInvoiceNo(), invoiceDTO.getOrgId())) {
					String errorMessage = String.format("This InvoiceNo: %s Already Exists in This Organization",
							invoiceDTO.getInvoiceNo());
					throw new ApplicationException(errorMessage);
				}
				invoiceVO.setInvoiceNo(invoiceDTO.getInvoiceNo().toUpperCase());
			}

			message = "Invoice Updated Successfully";
		} else {

//			// GETDOCID API
//
//			String docId = quotationRepo.getQuotationDocId(invoiceDTO.getOrgId(), invoiceDTO.getFinYear(),
//					invoiceDTO.getBranchCode(), screenCode);
//			invoiceVO.setDocId(docId);
//
//			// GETDOCID LASTNO +1
//			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
//					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(invoiceDTO.getOrgId(), invoiceDTO.getFinYear(),
//							invoiceDTO.getBranchCode(), screenCode);
//			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
//			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			if (invoiceRepo.existsByInvoiceNoAndOrgId(invoiceDTO.getInvoiceNo(), invoiceDTO.getOrgId())) {
				String errorMessage = String.format("This InvoiceNoInvoiceNo: %s Already Exists in This Organization",
						invoiceDTO.getInvoiceNo());
				throw new ApplicationException(errorMessage);
			}

			invoiceVO.setUpdatedBy(invoiceDTO.getCreatedBy());
			invoiceVO.setCreatedBy(invoiceDTO.getCreatedBy());
			message = "Invoice Created Successfully";
		}

		createUpdateInvoiceVOByInvoiceDTO(invoiceDTO, invoiceVO);
		invoiceRepo.save(invoiceVO);
		Map<String, Object> response = new HashMap<>();
		response.put("invoiceVO", invoiceVO);
		response.put("message", message);
		return response;
	}

	private void createUpdateInvoiceVOByInvoiceDTO(@Valid InvoiceDTO invoiceDTO, InvoiceVO invoiceVO)
			throws ApplicationException {
		invoiceVO.setStatementOf(invoiceDTO.getStatementOf());
		invoiceVO.setBranchCode(invoiceDTO.getBranchCode());
		invoiceVO.setVesselDetails(invoiceDTO.getVesselDetails());
		invoiceVO.setPol(invoiceDTO.getPol());
		invoiceVO.setCreatedBy(invoiceDTO.getCreatedBy());
		invoiceVO.setPod(invoiceDTO.getPod());
		invoiceVO.setBuyerDetails(invoiceDTO.getBuyerDetails());
		invoiceVO.setOurContractNo(invoiceDTO.getOurContractNo());
		invoiceVO.setInvoiceNo(invoiceDTO.getInvoiceNo());
		invoiceVO.setOrgId(invoiceDTO.getOrgId());

		if (invoiceDTO.getId() != null) {
			List<InvoiceDetailsVO> invoiceDetailsVO1 = invoiceDetailsRepo.findByInvoiceVO(invoiceVO);
			invoiceDetailsRepo.deleteAll(invoiceDetailsVO1);

		}

		BigDecimal totalamount = BigDecimal.ZERO;
		int totalPackages = 0;
		double totalWeight = 0.0;
		List<InvoiceDetailsVO> invoiceDetailsVOs = new ArrayList<>();
		if (invoiceDTO.getInvoiceDetailsDTO() != null) {
			for (InvoiceDetailsDTO invoiceDetailsDTO : invoiceDTO.getInvoiceDetailsDTO()) {
				InvoiceDetailsVO invoiceDetailsVO = new InvoiceDetailsVO();

				invoiceDetailsVO.setMarks(invoiceDetailsDTO.getMarks());
				invoiceDetailsVO.setAmount(invoiceDetailsDTO.getAmount());
				invoiceDetailsVO.setParticulars(invoiceDetailsDTO.getParticulars());
				invoiceDetailsVO.setHsCode(invoiceDetailsDTO.getHsCode());
				invoiceDetailsVO.setPriceAtUsd(invoiceDetailsDTO.getPriceAtUsd());
				totalamount = totalamount.add(invoiceDetailsVO.getAmount());
				invoiceDetailsVO.setPackages(invoiceDetailsDTO.getPackages());
				totalPackages = totalPackages + invoiceDetailsVO.getPackages();
				invoiceDetailsVO.setWeight(invoiceDetailsDTO.getWeight());
				totalWeight = totalWeight + invoiceDetailsVO.getWeight();

				invoiceDetailsVO.setInvoiceVO(invoiceVO);
				invoiceDetailsVOs.add(invoiceDetailsVO);
			}
		}

		invoiceVO.setTotalPackage(totalPackages);
		invoiceVO.setTotalNetWeight(totalWeight);
		invoiceVO.setTotalAmount(totalamount);
		invoiceVO.setAmountInWords(amountInWordsConverterService.convert(invoiceVO.getTotalAmount()));

		invoiceVO.setInvoiceDetailsVO(invoiceDetailsVOs);

	}

	// Certificate

	@Override
	public Map<String, Object> getAllCertificateByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("enameaddress").ascending());
		Page<CertificateVO> customerPage = certificateRepo.getAllCertificateByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public CertificateVO getCertificateById(Long id) {

		return certificateRepo.getCertificateById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateCertificate(@Valid CertificateDTO certificateDTO)
			throws ApplicationException {

		CertificateVO certificateVO = new CertificateVO();

		String message;

		if (ObjectUtils.isNotEmpty(certificateDTO.getId())) {

			certificateVO = certificateRepo.findById(certificateDTO.getId())
					.orElseThrow(() -> new ApplicationException("Certificate Not Found!"));
			certificateVO.setUpdatedBy(certificateDTO.getCreatedBy());

			message = "Certificate Updated Successfully";
		} else {

//			// GETDOCID API
//
//			String docId = quotationRepo.getQuotationDocId(invoiceDTO.getOrgId(), invoiceDTO.getFinYear(),
//					invoiceDTO.getBranchCode(), screenCode);
//			invoiceVO.setDocId(docId);
//
//			// GETDOCID LASTNO +1
//			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
//					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(invoiceDTO.getOrgId(), invoiceDTO.getFinYear(),
//							invoiceDTO.getBranchCode(), screenCode);
//			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
//			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			certificateVO.setUpdatedBy(certificateDTO.getCreatedBy());
			certificateVO.setCreatedBy(certificateDTO.getCreatedBy());
			message = "Certificate Created Successfully";
		}

		createUpdateCertificateVOByCertificateDTO(certificateDTO, certificateVO);
		certificateRepo.save(certificateVO);
		Map<String, Object> response = new HashMap<>();
		response.put("certificateVO", certificateVO);
		response.put("message", message);
		return response;
	}

	private void createUpdateCertificateVOByCertificateDTO(@Valid CertificateDTO certificateDTO,
			CertificateVO certificateVO) throws ApplicationException {
		certificateVO.setExporterNameAddress(certificateDTO.getExporterNameAddress());
		certificateVO.setBranchCode(certificateDTO.getBranchCode());
		certificateVO.setConsigneeNameAddress(certificateDTO.getConsigneeNameAddress());
		certificateVO.setNotifyPartyNameAddress(certificateDTO.getNotifyPartyNameAddress());
		certificateVO.setCreatedBy(certificateDTO.getCreatedBy());
		certificateVO.setVessel(certificateDTO.getVessel());
		certificateVO.setPartOfLoading(certificateDTO.getPartOfLoading());
		certificateVO.setDateOfDeparture(certificateDTO.getDateOfDeparture());
		certificateVO.setCountryOfOrginOfGoods(certificateDTO.getCountryOfOrginOfGoods());
		certificateVO.setOurSalesContractno(certificateDTO.getOurSalesContractno());
		certificateVO.setBlNo(certificateDTO.getBlNo());
		certificateVO.setOrgId(certificateDTO.getOrgId());

		if (certificateDTO.getId() != null) {
			List<CertificateDetailsVO> certificateDetailsVO1 = certificateDetailsRepo
					.findByCertificateVO(certificateVO);
			certificateDetailsRepo.deleteAll(certificateDetailsVO1);

		}

		int totalPackages = 0;
		double totalWeight = 0.0;
		List<CertificateDetailsVO> certificateDetailsVOs = new ArrayList<>();
		if (certificateDTO.getCertificateDetailsDTO() != null) {
			for (CertificateDetailsDTO certificateDetailsDTO : certificateDTO.getCertificateDetailsDTO()) {
				CertificateDetailsVO certificateDetailsVO = new CertificateDetailsVO();

				certificateDetailsVO.setMarksNumbers(certificateDetailsDTO.getMarksNumbers());
				certificateDetailsVO.setNumberOfPackagesQuantity(certificateDetailsDTO.getNumberOfPackagesQuantity());
				certificateDetailsVO.setDescriptionOfGoods(certificateDetailsDTO.getDescriptionOfGoods());
				certificateDetailsVO.setInvoiceNo(certificateDetailsDTO.getInvoiceNo());
				certificateDetailsVO.setContainerNo(certificateDetailsDTO.getContainerNo());
				certificateDetailsVO.setSealNo(certificateDetailsDTO.getSealNo());

				totalPackages = totalPackages + certificateDetailsDTO.getNumberOfPackagesQuantity();
				certificateDetailsVO.setWeight(certificateDetailsDTO.getWeight());
				totalWeight = totalWeight + certificateDetailsVO.getWeight();

				certificateDetailsVO.setCertificateVO(certificateVO);
				certificateDetailsVOs.add(certificateDetailsVO);
			}
		}

		certificateVO.setTotalPackages(totalPackages);
		certificateVO.setTotalNetWeight(totalWeight);
		certificateVO.setCertificateDetailsVO(certificateDetailsVOs);

	}

}
