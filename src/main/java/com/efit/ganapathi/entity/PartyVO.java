package com.efit.ganapathi.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "party")
public class PartyVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partygen")
	@SequenceGenerator(name = "partygen", sequenceName = "partyseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "partyid")
	private Long id;

	@Column(name = "partyname", nullable = false)
	private String partyName;

	@Column(name = "partycode", unique = true)
	private String partyCode;

	@Column(name = "contactperson")
	private String contactPerson;

	@Column(name = "partytype")
	private String partyType;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "status")
	private String status;

	@Column(name = "creditlimit")
	private BigDecimal creditLimit;

	// Common fields
	@Column(name = "branch")
	private String branch;

	@Column(name = "branchcode")
	private String branchCode;

	@Column(name = "active")
	private boolean active;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "modifiedby")
	private String updatedBy;

	@Column(name = "orgid")
	private Long orgId;

	@Column(name = "cancel")
	private boolean cancel;

	@Column(name = "screenname")
	private String screenName = "PARTY";

	@Column(name = "screencode")
	private String screenCode = "PS";

	// Custom JSON fields
	@JsonGetter("activeStatus")
	public String getActiveStatus() {
		return active ? "Active" : "In-Active";
	}

	@JsonGetter("cancelFlag")
	public String getCancelFlag() {
		return cancel ? "T" : "F";
	}
}
