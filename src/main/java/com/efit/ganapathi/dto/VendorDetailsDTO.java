package com.efit.ganapathi.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDetailsDTO {

	private Long id;
	private LocalDate effectioveTo;
	private LocalDate effectiveFrom;
	
//	private byte[] contractAttachment;
//	private byte[] backgroundVerification;
//	private byte[] securityCheck;

}