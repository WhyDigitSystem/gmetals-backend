package com.efit.ganapathi.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortDTO {
	private Long id;

	@NotBlank(message = "PortName is required")
	private String portName;

	@NotBlank(message = "PortCode is required")
	private String portCode;

	@NotNull(message = "countryId is required")
	@Min(value = 1000000001L, message = "countryId must be minimum 1000000001")
	@Max(value = 1999999999L, message = "countryId must be 10 digits starting with 1")
	private Long country;

	private boolean active;

	@NotBlank(message = "createdBy name is required")
	private String createdBy;

	@NotNull(message = "OrgId is required")
	private Long orgId;

	private boolean cancel;
}
