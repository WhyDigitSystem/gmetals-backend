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
	private Long countryName;

	private boolean active;

	@NotBlank(message = "createdBy name is required")
	private String createdBy;

	@NotNull(message = "OrgId is required")
	private Long orgId;

	private boolean cancel;
}
