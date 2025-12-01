package com.efit.ganapathi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExporterDTO {

	private Long id;

	private String companyName;
	
	private String shortName;

	private String contactPerson;

	private String taxId;

	private String address;

	private String phone;

	private String email;

	// common fields

	private String branch;

	private String branchCode;

	private boolean active;
	
	private Long orgId;

	private String createdBy;

}
