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
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productgen")
	@SequenceGenerator(name = "productgen", sequenceName = "productseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "productid")
	private Long id;

	@Column(name = "pname")
	private String productName;
	@Column(name = "pcode")
	private String productCode;	
	@Column(name = "category")
	private String category;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "subcategory")
	private String subCategory;
	@Column(name = "uom")
	private String uom;
	@Column(name = "price")
	private double price;
	
	
	@Column(name = "cancel")
	private boolean cancel=false;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "active")
	private boolean active =true;

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