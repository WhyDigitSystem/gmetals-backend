package com.efit.ganapathi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long id;
	@NotBlank(message = "Please enter Product Name")
	private String productName;

	@NotBlank(message = "Please enter Product Code")
	private String productCode;

	@NotBlank(message = "Please select Category")
	private String category;

	@NotNull(message = "Please select Organization")
	private Long orgId;

	@NotBlank(message = "Please select Sub Category")
	private String subCategory;

	@NotBlank(message = "Please enter Unit of Measure")
	private String uom;

	@Positive(message = "Price must be greater than zero")
	private double price;

	@NotBlank(message = "CreatedBy is required")
	private String createdBy;
	
	@NotBlank(message = "Status is required")
	private String status;

	private boolean active;
	private boolean cancel;

}
