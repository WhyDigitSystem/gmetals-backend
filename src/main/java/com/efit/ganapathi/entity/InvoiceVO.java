package com.efit.ganapathi.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoicegen")
	@SequenceGenerator(name = "invoicegen", sequenceName = "invoiceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "invoiceid")
	private Long id;

//	@Column(name = "docid")
//	private String docId;
//	@Column(name = "docdate")
//	private LocalDate docDate = LocalDate.now();
	@Column(name = "statementof")
	private String statementOf;
	@Column(name = "vesseldetails")
	private String vesselDetails;
	@Column(name = "pol")
	private String pol;
	@Column(name = "pod")
	private String pod;
	@Column(name = "buyerdetails")
	private String buyerDetails;
	@Column(name = "ourcontractno")
	private String ourContractNo;
	@Column(name = "invoiceno")
	private String invoiceNo;
	@Column(name = "totalnetweight")
	private double totalNetWeight;
	@Column(name = "totalamount", precision = 10, scale = 2)
	private BigDecimal totalAmount;

	@Column(name = "amountinwords")
	private String amountInWords;
	@Column(name = "invoicedate")
	private LocalDate invoiceDate = LocalDate.now();
	@Column(name = "totalpackage")
	private int totalPackage;

//	@Column(name = "finyear", length = 5)
//	private String finYear;

	@Column(name = "screencode", length = 5)
	private String screenCode = "IN";

	@Column(name = "screenname", length = 25)
	private String screenName = "INVOICE";

//	@Column(name = "branch", length = 25)
//	private String branch;

	@Column(name = "branchcode", length = 20)
	private String branchCode;

	@Column(name = "orgid")
	private Long orgId;
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

	@OneToMany(mappedBy = "invoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<InvoiceDetailsVO> invoiceDetailsVO;

	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}

	@JsonGetter("cancel")
	public String getCancel() {
		return cancel ? "T" : "F";
	}

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
