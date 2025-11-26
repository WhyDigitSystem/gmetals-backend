package com.efit.ganapathi.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryDTO {
	private Long id;
	private String customerId;
	private String customerName;
	private String customerConcatNumbers;
	private String customerEmailPhone;
	private String customerAddress;
	private String enquiryType;
	private String status;
	private String customerStatus;
	private Date fromRange;
	private Date toRange;
//	private String enquiryId;
	private String eType;
	private String source;
	private String subject;
	private String description;
	private String priority;
	private String enquiryStatus;
	private String assignedAgent;
	private double slaHours;
	private String customerSentiment;
	private int attachmentsCount;
	private String addInteractionNote;
	private String internalNotes;
	private String branch;
	private String branchCode;
	private Long orgId;
	private boolean cancel;
	private String createdBy;
	private boolean active;

}
