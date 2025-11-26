package com.efit.ganapathi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

	private Long id;
	private String vendorCode;
	private String status;
	private String organization;
	private String approvalStatus;
	private String primaryPhoneNumber;
	private String primaryEmail;
	private String additionalPhoneNumber;
	private String additionalEmails;
	private String gst;
	private String address;
	private String accountNumber;
	private String ifsc;
	private String accountHolderName;

	private String vendorType;
	private double advancePercent;
	private String creditPeriod;
	private double tdsPercent;
	private String vendorSpotId;
	private String vendoruuid;
	private String tags;
	
	private String pocName;
	private String pocEmail;
	private String pocNumber;

	private boolean active ;
	private String createdBy;
	private Long orgId;
	private String branchCode;
	private String branch;
	
	List<VendorUsersDTO> vendorUsersDTO;
	List<VendorDetailsDTO> vendorDetailsDTO;

}
