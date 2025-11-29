package com.efit.ganapathi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.efit.ganapathi.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "form9")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form9VO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form9gen")
	@SequenceGenerator(name = "form9gen", sequenceName = "form9seq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "form9id")
	private Long id;

	@Column(name = "exporter")
	private String exporter;
	@Column(name = "contactperson")
	private String contactPerson;
	@Column(name = "tel")
	private String tel;
	@Column(name = "fax")
	private String fax;
	@Column(name = "wgenerator")
	private String wGenerator;
	@Column(name = "wgeneratorperson")
	private String wGeneratorPerson;
	@Column(name = "generationsite")
	private String generationSite;
	@Column(name = "importer")
	private String importer;
	@Column(name = "refno")
	private String refNo;
	@Column(name = "movementype")
	private String movementType;

	@Column(name = "shipmentno")
	private String shipmentNo;
	@Column(name = "disposername")
	private String disposerName;
	@Column(name = "disposerperson")
	private String disposerPerson;
	@Column(name = "disposalsite")
	private String disposalSite;
	@Column(name = "disposertelfax")
	private String disposerTelFax;
	@Column(name = "recoverymode")
	private String recoveryMode;
	@Column(name = "rcode")
	private String rCode;
	@Column(name = "attachments")
	private String attachments;
	@Column(name = "designation")
	private String designation;
	@Column(name = "physical")
	private String physical;
	@Column(name = "quantity")
	private double quantity;
	@Column(name = "wastecode")
	private String wasteCode;
	@Column(name = "baseno")
	private String baseNo;
	@Column(name = "oecdno")
	private String oecdNo;
	@Column(name = "unno")
	private String unNo;
	@Column(name = "itchs")
	private String itchs;
	@Column(name = "customscode")
	private String customsCode;
	@Column(name = "other")
	private String other;
	@Column(name = "oecdclassification")
	private String oecdClassification;
	@Column(name = "packingtype")
	private String packingType;
	@Column(name = "number")
	private String number;
	@Column(name = "unclassification")
	private String unClassification;
	@Column(name = "unshippingname")
	private String unShippingName;
	@Column(name = "unidentificationno")
	private String unIdentificationNo;
	@Column(name = "unclass")
	private String unClass;
	@Column(name = "hnumber")
	private String hNumber;
	@Column(name = "ynumber")
	private String yNumber;

	@Column(name = "shrequirements")
	private String shrequirements;
	@Column(name = "shipmentdate")
	private String shipmentDate;
	@Column(name = "receivedby")
	private String receivedby;

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

	@OneToMany(mappedBy = "form9VO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<Form9DetailsVO> form9DetailsVO;

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
