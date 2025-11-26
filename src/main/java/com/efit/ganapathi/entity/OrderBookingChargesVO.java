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
@Table(name = "orderbookingcharges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookingChargesVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderbookingchargesgen")
	@SequenceGenerator(name = "orderbookingchargesgen", sequenceName = "orderbookingchargesseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "orderbookingchargesid")
	private Long id;

	@Column(name = "additionalcharges")
	private String additionalCharges;

	@Column(name = "amount", precision = 10, scale = 2)
	private BigDecimal amount;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "orderbookingid")
	private OrderBookingVO orderBookingVO;

}
