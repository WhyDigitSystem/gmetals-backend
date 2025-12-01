package com.efit.ganapathi.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {
	private Long id;
	@NotBlank
	private String exporterNameAddress;
	@NotBlank
	private String consigneeNameAddress;
	@NotBlank
	private String notifyPartyNameAddress;

	@NotBlank
	private String vessel;

	@NotBlank
	private String blNo;
	@NotBlank
	private String partOfLoading;
	@NotNull
	private LocalDate dateOfDeparture;
	@NotNull
	private Double totalNetWeight;
	@NotBlank
	private String ourSalesContractno;
	@NotBlank
	private String countryOfOrginOfGoods;
	@NotBlank
	private String branchCode;
	@NotNull
	private Long orgId;
	@NotBlank
	private String createdBy;

	private List<CertificateDetailsDTO> certificateDetailsDTO;
}
