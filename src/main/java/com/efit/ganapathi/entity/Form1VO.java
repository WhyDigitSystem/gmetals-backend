package com.efit.ganapathi.entity;

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
@Table(name = "form1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form1VO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form1gen")
	@SequenceGenerator(name = "form1gen", sequenceName = "form1seq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "form1id")
	private Long id;

	@Column(name = "goodsdescription")
	private String goodsDescription;

	@Column(name = "production")
	private String production;

	@Column(name = "origin")
	private String origin;

	@Column(name = "wobtained")
	private Boolean wObtained;

	@Column(name = "wexplanation")
	private String wExplanation;

	@Column(name = "import")
	private String importValue;

	@Column(name = "hscode")
	private String hsCode;

	@Column(name = "component")
	private String component;

	@Column(name = "manufactured")
	private Boolean manufactured;

	@Column(name = "procured")
	private Boolean procured;

	@Column(name = "confirmation")
	private Boolean confirmation;

	@Column(name = "minimisused")
	private Boolean minimisUsed;

	@Column(name = "minimisdescription")
	private String minimisDescription;

	@Column(name = "accumulationused")
	private Boolean accumulationUsed;

	@Column(name = "accumulationdescription")
	private String accumulationDescription;

	@Column(name = "imaterialused")
	private Boolean iMaterialUsed;

	@Column(name = "imaterialdescription")
	private String iMaterialDescription;

	@Column(name = "content")
	private Boolean content;

	@Column(name = "ctcruleapplied")
	private Boolean ctcRuleApplied;

	@Column(name = "orginhscode")
	private String originHsCode;

	@Column(name = "ruleapplied")
	private Boolean ruleApplied;

	@Column(name = "ruletype")
	private String ruleType;

	@Column(name = "retrospectively")
	private Boolean retrospectively;

	@Column(name = "retrospectivereason")
	private String retrospectiveReason;

	@Column(name = "directlyshipped")
	private Boolean directlyShipped;

	@Column(name = "directshipmentverified")
	private String directShipmentVerified;

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
