package com.efit.ganapathi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.efit.ganapathi.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "porthdr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porthdrgen")
	@SequenceGenerator(name = "porthdrgen", sequenceName = "porthdrseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "porthdrid")
	private Long id;

	@Column(name = "portname")
	private String portName;
	@Column(name = "portcode")
	private String portCode;
//	@Column(name = "countryId")
//	private Long countryId;

	@Column(name = "active")
	private boolean active = true;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "cancel")
	private boolean cancel=false;
	
	@ManyToOne
	@JoinColumn(name="countryid")
	private CountryVO countryName;

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