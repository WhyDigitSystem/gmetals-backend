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
@Table(name = "packinglistdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingListDetailsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packinglistdetailsgen")
	@SequenceGenerator(name = "packinglistdetailsgen", sequenceName = "packinglistdetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "packinglistdetailsid")
	private Long id;

	@Column(name = "itemname")
	private String itemName;

	@Column(name = "itemcode")
	private String itemCode;

	@Column(name = "itemdescription")
	private String itemDescription;

	@Column(name = "box")
	private double box;

	
	@Column(name = "weight",precision = 10, scale = 2)
	private BigDecimal weight;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "packinglistid")
	private PackingListVO packingListVO;

}
