package com.efit.ganapathi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long id;
	private String productName;
	private String productCode;
	private String category;
	private Long orgId;
	private String subCategory;
	private String uom;
	private double price;
	private String createdBy;
	private boolean active;
	private boolean cancel;
	

}
