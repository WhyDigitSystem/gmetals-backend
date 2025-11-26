package com.efit.ganapathi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VechicleDTO {

	private Long id;
	private String vechicleNumber;
	private String vechicleType;
	private String status;
	
	private String branch;
	private String branchCode;
	private String createdBy;
	private Long orgId;

}
