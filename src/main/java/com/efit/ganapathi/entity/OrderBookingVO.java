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
import javax.persistence.Lob;
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
@Table(name = "orderbooking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookingVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderbookinggen")
	@SequenceGenerator(name = "orderbookinggen", sequenceName = "orderbookingseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "orderbookingid")
	private Long id;

//	@Column(name = "docid")
//	private String docId;
//	@Column(name = "docdate")
//	private LocalDate docDate = LocalDate.now();
	@Column(name = "sname")
	private String shipperName;
	@Column(name = "scontactperson")
	private String shipperContactPerson;
	@Column(name = "sphonemail")
	private String shipperPhonEmail;
	@Column(name = "saddress")
	private String shipperAddress;
	@Column(name = "cname")
	private String consigneeName;
	@Column(name = "ccontactperson")
	private String consigneeContactPerson;
	@Column(name = "cphoneemail")
	private String consigneePhoneEmail;
	@Column(name = "caddress")
	private String consigneeAddress;

	@Column(name = "cargotype")
	private String cargoType;
	@Column(name = "packagingtype")
	private String packagingType;
	@Column(name = "descriptions")
	private String descriptions;
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "length", precision = 10, scale = 2)
	private BigDecimal length;
	@Column(name = "width", precision = 10, scale = 2)
	private BigDecimal width;
	@Column(name = "height", precision = 10, scale = 2)
	private BigDecimal height;
	@Column(name = "weight", precision = 10, scale = 2)
	private BigDecimal weight;

	@Column(name = "pickupdate")
	private LocalDate pickupDate;
	@Column(name = "pickuptimestart")
	private String pickupTimeStart;
	@Column(name = "pickuptimeend")
	private String pickupTimeEnd;

	@Column(name = "pickuplocation")
	private String pickuplocation;

	@Column(name = "deliverydate")
	private LocalDate deliveryDate;
	@Column(name = "deliverytimestart")
	private String deliveryTimeStart;
	@Column(name = "deliverytimeend")
	private String deliveryTimeEnd;

	@Column(name = "deliverylocation")
	private String deliverylocation;

	@Column(name = "transportmode")
	private String transportMode;
	@Column(name = "servicelevel")
	private String serviceLevel;
	@Column(name = "vehicletype")
	private String vehicleType;
	@Column(name = "freightcharges ", precision = 10, scale = 2)
	private BigDecimal freightCharges;
	@Column(name = "paymentmethod")
	private String paymentMethod;

	@Lob
	@Column(name = "attachments")
	private byte[] attachments;
	
	@Column(name="notes")
	private String notes;
	

	// summary

	@Column(name = "totalcost", precision = 10, scale = 2)
	private BigDecimal totalCost;
	@Column(name = "totalvolume", precision = 10, scale = 2)
	private BigDecimal totalVolume;
	@Column(name = "esttransittime")
	private String estTransitTime;

//	@Column(name = "finyear", length = 5)
//	private String finYear;

	@Column(name = "screencode", length = 5)
	private String screenCode = "OB";

	@Column(name = "screenname", length = 25)
	private String screenName = "ORDER BOOKING";

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

	@OneToMany(mappedBy = "orderBookingVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<OrderBookingChargesVO> orderBookingChargesVO;

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
