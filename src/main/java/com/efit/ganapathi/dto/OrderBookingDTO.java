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
public class OrderBookingDTO {
	private Long id;
	private String shipperName;
	private String shipperContactPerson;
	private String shipperPhonEmail;
	private String shipperAddress;
	private String consigneeName;
	private String consigneeContactPerson;
	private String consigneePhoneEmail;
	private String consigneeAddress;
	private String cargoType;
	private String packagingType;
	private String descriptions;
	private int quantity;
	private BigDecimal length;
	private BigDecimal width;
	private BigDecimal height;
	private BigDecimal weight;

	private LocalDate pickupDate;
	private String pickupTimeStart;
	private String pickupTimeEnd;
	private String pickuplocation;
	private LocalDate deliveryDate;
	private String deliveryTimeStart;
	private String deliveryTimeEnd;
	private String deliverylocation;

	private String transportMode;
	private String serviceLevel;
	private String vehicleType;
	private BigDecimal freightCharges;
	private String paymentMethod;
	private String notes;
	private String estTransitTime;

	private String branchCode;

	private Long orgId;
	private String createdBy;

	private List<OrderBookingChargesDTO> orderBookingChargesDTO;
}
