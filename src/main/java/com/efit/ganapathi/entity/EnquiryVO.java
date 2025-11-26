package com.efit.ganapathi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.efit.ganapathi.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enquiry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enquirygen")
	@SequenceGenerator(name = "enquirygen", sequenceName = "enquiryseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "enquiryid")
	private Long id;

	@Column(name = "customerid")
	private String customerId;

	@Column(name = "cname")
	private String customerName;

	@Column(name = "cconcatnumbers")
	private String customerConcatNumbers;

	@Column(name = "cemailhone")
	private String customerEmailPhone;

	@Column(name = "caddress")
	private String customerAddress;

	@Column(name = "enquirytype")
	private String enquiryType;

	@Column(name = "status")
	private String status;

	@Column(name = "cstatus")
	private String customerStatus;

	@Column(name = "fromrange")
	private Date fromRange;

	@Column(name = "torange")
	private Date toRange;

	@Column(name = "eid", unique = true)
	private int enquiryId;

	@Column(name = "etype")
	private String eType;

	@Column(name = "source")
	private String source;

	@Column(name = "subject")
	private String subject;

	@Column(name = "description")
	private String description;

	@Column(name = "priority")
	private String priority;

	@Column(name = "estatus")
	private String enquiryStatus;

	@Column(name = "assignedagent")
	private String assignedAgent;

	@Column(name = "slahours")
	private double slaHours;

	@Column(name = "customersentiment")
	private String customerSentiment;

	@Column(name = "attachmentscount")
	private int attachmentsCount;

	@Column(name = "addinteractionnote")
	private String addInteractionNote;

	@Column(name = "internalnotes")
	private String internalNotes;

	@Column(name = "branch")
	private String branch;

//	@Column(name = "finyear", length = 5)
//	private String finYear;

	@Column(name = "screencode", length = 5)
	private String screenCode = "EN";

	@Column(name = "screenname", length = 25)
	private String screenName = "ENQUIRY";

//	@Column(name = "branch", length = 25)
//	private String branch;

	@Column(name = "branchcode", length = 20)
	private String branchCode;

	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "cancel")
	private boolean cancel = false;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "active")
	private boolean active = true;

	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}

	// Optionally, if you want to control serialization for 'cancel' field similarly
	@JsonGetter("cancel")
	public String getCancel() {
		return cancel ? "T" : "F";
	}

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
