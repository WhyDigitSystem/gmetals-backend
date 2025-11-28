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
@Table(name = "vessel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VesselVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vesselgen")
	@SequenceGenerator(name = "vesselgen", sequenceName = "vesselseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "vesselid")
	private Long id;

	@Column(name = "vesselvname")
	private String vesselName;
	@Column(name = "vesselcode")
	private String vesselCode;
	@Column(name = "imonumber")
	private String imoNumber;
	@Column(name = "type")
	private String type;
	@Column(name = "status")
	private String status;
	@Column(name = "carriername")
	private String carrierName;
	
	
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
