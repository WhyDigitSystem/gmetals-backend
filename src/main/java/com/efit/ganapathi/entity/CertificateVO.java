package com.efit.ganapathi.entity;

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
@Table(name = "certificate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificategen")
	@SequenceGenerator(name = "certificategen", sequenceName = "certificateseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "certificateid")
	private Long id;

//	@Column(name = "docid")
//	private String docId;
//	@Column(name = "docdate")
//	private LocalDate docDate = LocalDate.now();
	@Column(name = "enameaddress")
	private String exporterNameAddress;
	@Column(name = "cnameaddress")
	private String consigneeNameAddress;
	@Column(name = "npnameaddress")
	private String notifyPartyNameAddress;
	@Column(name = "vessel")
	private String vessel;
	@Column(name = "blno")
	private String blNo;
	@Column(name = "partofloading")
	private String partOfLoading;
	@Column(name = "dateofdeparture")
	private LocalDate dateOfDeparture;
	@Column(name = "totalnetweight")
	private double totalNetWeight;
	@Column(name = "oursalescontractno")
	private String ourSalesContractno;

	@Column(name = "countryoforginofgoods")
	private String countryOfOrginOfGoods;
	@Column(name = "invoicedate")
	private LocalDate invoiceDate = LocalDate.now();
	@Column(name = "totalpackages")
	private int totalPackages;

//	@Column(name = "finyear", length = 5)
//	private String finYear;

	@Column(name = "screencode", length = 5)
	private String screenCode = "CI";

	@Column(name = "screenname", length = 25)
	private String screenName = "CERTIFICATE";

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

	@OneToMany(mappedBy = "certificateVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<CertificateDetailsVO> certificateDetailsVO;

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