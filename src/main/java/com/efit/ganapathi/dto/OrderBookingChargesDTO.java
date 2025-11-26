package com.efit.ganapathi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookingChargesDTO {
	private String additionalCharges;
	private BigDecimal amount;

}
