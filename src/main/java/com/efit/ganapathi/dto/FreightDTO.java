package com.efit.ganapathi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreightDTO {
	private Long id;

	@NotBlank(message = "Please enter Freight Name")
	private String freightName;

	@NotBlank(message = "Please enter Freight Code")
	private String freightCode;

	@NotBlank(message = "Please enter Description")
	private String description;

	@NotBlank(message = "Please enter From Location")
	private String fromLocation;

	@NotBlank(message = "Please enter To Location")
	private String toLocation;

	@Positive(message = "Weight must be greater than 0")
	private double weight;

	@NotNull(message = "OrgId is required")
	private Long orgId;

	@PositiveOrZero(message = "Freight Charges must be zero or positive")
	private double freightCharges;

	@NotNull(message = "createdBy Id is required")
	private String createdBy;

}
