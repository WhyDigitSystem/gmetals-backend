package com.efit.ganapathi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
	private Long id;
	private String statementOf;
	private String vesselDetails;
	private String pol;
	private String pod;
	private String buyerDetails;
	private String ourContractNo;
	private String invoiceNo;

	private String branchCode;
	private Long orgId;
	private String createdBy;

	private List<InvoiceDetailsDTO>invoiceDetailsDTO;
}
