package com.efit.ganapathi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDTO {
	private String marks;
	private int packages;
	private String particulars;
	private String hsCode;
	private double weight;
	private BigDecimal amount;
	private BigDecimal priceAtUsd;

}
