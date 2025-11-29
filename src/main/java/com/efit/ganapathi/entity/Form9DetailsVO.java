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
@Table(name = "form9details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form9DetailsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form9detailsgen")
	@SequenceGenerator(name = "form9detailsgen", sequenceName = "form9detailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "form9detailsid")
	private Long id;

	@Column(name = "nameaddress")
	private String nameAddress;

	@Column(name = "registrationno")
	private String registrationNo;

	@Column(name = "telfax")
	private String telFax;

	@Column(name = "transport")
	private String transport;

	@Column(name = "dateoftransfer")
	private String dateoftransfer;

	@Column(name = "signature")
	private String signature;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "form9id")
	private Form9VO form9VO;

}
