package com.efit.ganapathi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form9DTO {
	private Long id;
	private String exporter;
	private String contactPerson;
	private String tel;
	private String fax;
	private String wGenerator;
	private String wGeneratorPerson;
	private String generationSite;
	private String importer;
	private String refNo;
	private String movementType;
	private String shipmentNo;
	private String disposerName;
	private String disposerPerson;
	private String disposalSite;
	private String disposerTelFax;
	private String recoveryMode;
	private String rCode;
	private String attachments;
	private String designation;
	private String physical;
	private double quantity;
	private String wasteCode;
	private String baseNo;
	private String oecdNo;
	private String unNo;
	private String itchs;
	private String customsCode;
	private String other;
	private String oecdClassification;
	private String packingType;
	private String number;
	private String unClassification;
	private String unShippingName;
	private String unIdentificationNo;
	private String unClass;
	private String hNumber;
	private String yNumber;
	private String shrequirements;
	private String shipmentDate;
	private String receivedby;
	private String createdBy;
	private Long orgId;
	
private List<Form9DetailsDTO>form9DetailsDTO;
}
