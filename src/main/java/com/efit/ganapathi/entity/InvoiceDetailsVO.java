package com.efit.ganapathi.entity;

import java.math.BigDecimal;

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
@Table(name = "invoicedetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoicedetailsgen")
	@SequenceGenerator(name = "invoicedetailsgen", sequenceName = "invoicedetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "invoicedetailsid")
	private Long id;

	@Column(name = "marks")
	private String marks;

	@Column(name = "packages")
	private int packages;

	@Column(name = "particulars")
	private String particulars;
	@Column(name = "hscode")
	private String hsCode;

	@Column(name = "weight")
	private double weight;

	@Column(name = "amount", precision = 10, scale = 2)
	private BigDecimal amount;
	@Column(name = "priceatusd", precision = 10, scale = 2)
	private BigDecimal priceAtUsd;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "invoiceid")
	private InvoiceVO invoiceVO;

}
