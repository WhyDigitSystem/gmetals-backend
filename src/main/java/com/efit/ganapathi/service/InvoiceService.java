package com.efit.ganapathi.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.efit.ganapathi.dto.CertificateDTO;
import com.efit.ganapathi.dto.InvoiceDTO;
import com.efit.ganapathi.entity.CertificateVO;
import com.efit.ganapathi.entity.InvoiceVO;
import com.efit.ganapathi.exception.ApplicationException;

@Service
public interface InvoiceService {

	// Invoice

	Map<String, Object> getAllInvoiceByOrgId(Long orgId, String search, int page, int size);

	InvoiceVO getInvoiceById(Long id);

	Map<String, Object> updateCreateInvoice(InvoiceDTO invoiceDTO) throws ApplicationException;

	// Certificate

	Map<String, Object> getAllCertificateByOrgId(Long orgId, String search, int page, int size);

	CertificateVO getCertificateById(Long id);

	Map<String, Object> updateCreateCertificate(@Valid CertificateDTO certificateDTO) throws ApplicationException;

}
