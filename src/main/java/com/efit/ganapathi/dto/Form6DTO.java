package com.efit.ganapathi.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form6DTO {
	private Long id;

	private String exporter;

	private String contactPerson;

	private String exporterEmail;

	private String wGenerator;

	private String wGeneratorPerson;

	private String generatorEmail;

	private String generationSite;

	private String importer;

	private String importerPerson;

	private String importerEmail;

	private String trader;

	private String traderPerson;

	private String traderEmail;

	private String actualUserDetails;

	private String refNo;

	private String bill;

	private String countryImEx;

	private String description;

	private String physical;

	private double quantity;

	private String chemical;

	private String baselNo;

	private String unShippingName;

	private String unClass;

	private String unNo;

	private String hNumber;

	private String yNumber;

	private String itchs;

	private String customsCode;

	private String other;

	private String packages;

	private String number;

	private String requirements;

	private String movement;

	private String multiple;

	private String expected;

	private String estimated;

	private String transporter;

	private String transporterPerson;

	private String transporterEmail;

	private String registrationNo;

	private String transportType;

	private LocalDate transferDate;

	private String declaration;

	private String completedImporter;

	private String shipmentReceived;

	private String recovery;

	private String rCode;

	private String technology;

	private String specificConditions;

	private String createdBy;

	private Long orgId;

}
