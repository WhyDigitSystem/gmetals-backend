package com.efit.ganapathi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VesselDTO {
	private Long id;

	@NotBlank(message = "Vessel name is required")
	private String vesselName;

	@NotBlank(message = "Vessel code is required")
	private String vesselCode;

	@NotBlank(message = "IMO number is required")
	private String imoNumber;

	@NotBlank(message = "Vessel type is required")
	private String type;

	@NotBlank(message = "Vessel status is required")
	private String status;

	@NotBlank(message = "Carrier name is required")
	private String carrierName;

	private boolean active;

	@NotBlank(message = "CreatedBy cannot be blank")
	private String createdBy;

	@NotNull(message = "OrgId is required")
	private Long orgId;

	private boolean cancel;
}
