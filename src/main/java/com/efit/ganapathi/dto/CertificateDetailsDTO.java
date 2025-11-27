package com.efit.ganapathi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDetailsDTO {
	private String marksNumbers;
	private int numberOfPackagesQuantity;
	private String descriptionOfGoods;
	private String invoiceNo;
	private String containerNo;
	private String sealNo;
	private double weight;

}
