package com.efit.ganapathi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingListDetailsDTO {

	private String itemName;

	private String itemCode;

	private String itemDescription;

	private double box;

	private BigDecimal weight;
}
