package com.efit.ganapathi.entity;

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
@Table(name = "exporter")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExporterVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exportergen")
	@SequenceGenerator(name = "exportergen", sequenceName = "exporterseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "exporterid")
	private Long id;

	@Column(nullable = false, name = "companyname")
	private String companyName;
	
	@Column(name = "shortname")
	private String shortName;

	@Column(name = "contactperson")
	private String contactPerson;

	@Column(nullable = false,name = "taxid")
	private String taxId;

	@Column(nullable = false,name = "address")
	private String address;

	@Column(nullable = false,name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	// common fields

	@Column(name = "branch")
	private String branch;

	@Column(name = "branchcode")
	private String branchCode;

	@Column(name = "active")
	private boolean active = true;

	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "status")
	private String status;

	@Column(name = "modifiedby")
	private String updatedBy;

	@Column(name = "orgid")
	private Long orgId;

	@Column(name = "cancel")
	private boolean cancel;

	@Column(name = "screenname")
	private String screenName = "EXPORTER";

	@Column(name = "screencode")
	private String screenCode = "ES";

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

	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

}
