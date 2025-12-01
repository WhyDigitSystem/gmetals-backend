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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "certificatedetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDetailsVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificatedetailsgen")
	@SequenceGenerator(name = "certificatedetailsgen", sequenceName = "certificatedetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "certificatedetailsid")
	private Long id;

	@Column(name = "marksnumbers")
	private String marksNumbers;

	@Column(name = "numberofpackagesquantity")
	private int numberOfPackagesQuantity;

	@Column(name = "descriptionofgoods")
	private String descriptionOfGoods;
	@Column(name = "invoiceno")
	private String invoiceNo;
	@Column(name = "containerno")
	private String containerNo;
	@Column(name = "sealno")
	private String sealNo;

	@Column(name = "weight")
	private double weight;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "certificateid")
	private CertificateVO certificateVO;

}
