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
@Table(name = "packinglist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingListVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packinglistgen")
	@SequenceGenerator(name = "packinglistgen", sequenceName = "packinglistseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "packinglistid")
	private Long id;

	@Column(name = "exporter")
	private String exporter;
	@Column(name = "importer")
	private String importer;
	@Column(name = "packinglistnumber")
	private String packingListNumber;
	@Column(name = "packingdate")
	private LocalDate packingDate;
	@Column(name = "packingtype")
	private String packingType;
	@Column(name = "totalboxes")
	private double totalBoxes;
	@Column(name = "totalweight",precision = 10, scale = 2)
	private BigDecimal totalWeight;
	

	@Column(name = "screencode", length = 5)
	private String screenCode = "PL";

	@Column(name = "screenname", length = 25)
	private String screenName = "PACKINGLIST";

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

	@OneToMany(mappedBy = "packingListVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<PackingListDetailsVO> packingListDetailsVO;

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
