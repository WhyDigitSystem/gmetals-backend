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
@Table(name = "freight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreightVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "freightgen")
	@SequenceGenerator(name = "freightgen", sequenceName = "freightseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "freightid")
	private Long id;

	@Column(name = "freightname")
	private String freightName;
	@Column(name = "freightcode")
	private String freightCode;
	@Column(name = "description")
	private String description;
	@Column(name = "fromlocation")
	private String fromLocation;
	@Column(name = "tolocation")
	private String toLocation;
	@Column(name = "weight")
	private double weight;

	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "freightcharges")
	private double freightCharges;

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