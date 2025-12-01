package com.efit.ganapathi.entity;

import java.time.LocalDate;

import javax.persistence.Column;
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
@Table(name = "form6")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form6VO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form6gen")
	@SequenceGenerator(name = "form6gen", sequenceName = "form6seq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "form6id")
	private Long id;

	@Column(name = "exporter")
	private String exporter;

	@Column(name = "contactperson")
	private String contactPerson;

	@Column(name = "exporteremail")
	private String exporterEmail;

	@Column(name = "wgenerator")
	private String wGenerator;

	@Column(name = "wgeneratorperson")
	private String wGeneratorPerson;

	@Column(name = "generatoremail")
	private String generatorEmail;

	@Column(name = "generationsite")
	private String generationSite;

	@Column(name = "importer")
	private String importer;

	@Column(name = "importerperson")
	private String importerPerson;

	@Column(name = "importeremail")
	private String importerEmail;

	@Column(name = "trader")
	private String trader;

	@Column(name = "traderperson")
	private String traderPerson;

	@Column(name = "traderemail")
	private String traderEmail;

	@Column(name = "actualuserdetails")
	private String actualUserDetails;

	@Column(name = "refno")
	private String refNo;

	@Column(name = "bill")
	private String bill;

	@Column(name = "countryimex")
	private String countryImEx;

	@Column(name = "description")
	private String description;

	@Column(name = "physical")
	private String physical;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "chemical")
	private String chemical;

	@Column(name = "baselno")
	private String baselNo;

	@Column(name = "unshippingname")
	private String unShippingName;

	@Column(name = "unclass")
	private String unClass;

	@Column(name = "unno")
	private String unNo;

	@Column(name = "hnumber")
	private String hNumber;

	@Column(name = "ynumber")
	private String yNumber;

	@Column(name = "itchs")
	private String itchs;

	@Column(name = "customscode")
	private String customsCode;

	@Column(name = "other")
	private String other;

	@Column(name = "packages")
	private String packages;

	@Column(name = "number")
	private String number;

	@Column(name = "requirements")
	private String requirements;

	@Column(name = "movement")
	private String movement;

	@Column(name = "multiple")
	private String multiple;

	@Column(name = "expected")
	private String expected;

	@Column(name = "estimated")
	private String estimated;

	@Column(name = "transporter")
	private String transporter;

	@Column(name = "transporterperson")
	private String transporterPerson;

	@Column(name = "transporteremail")
	private String transporterEmail;

	@Column(name = "registrationno")
	private String registrationNo;

	@Column(name = "transporttype")
	private String transportType;

	@Column(name = "transferdate")
	private LocalDate transferDate;

	@Column(name = "declaration")
	private String declaration;

	@Column(name = "completedimporter")
	private String completedImporter;

	@Column(name = "shipmentreceived")
	private String shipmentReceived;

	@Column(name = "recovery")
	private String recovery;

	@Column(name = "rcode")
	private String rCode;

	@Column(name = "technology")
	private String technology;

	@Column(name = "specificconditions")
	private String specificConditions;

	@Column(name = "active")
	private boolean active = true;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "cancel")
	private boolean cancel = false;

	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}

	@JsonGetter("cancel")
	public String getCancel() {
		return cancel ? "T" : "F";
	}

	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
