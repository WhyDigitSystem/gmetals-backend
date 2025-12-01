package com.efit.ganapathi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form1DTO {
	private Long id;

	private String goodsDescription;

	private String production;

	private String origin;

	private Boolean wObtained;

	private String wExplanation;

	private String importValue;

	private String hsCode;

	private String component;

	private Boolean manufactured;

	private Boolean procured;

	private Boolean confirmation;

	private Boolean minimisUsed;

	private String minimisDescription;

	private Boolean accumulationUsed;

	private String accumulationDescription;

	private Boolean iMaterialUsed;

	private String iMaterialDescription;

	private Boolean content;

	private Boolean ctcRuleApplied;

	private String originHsCode;

	private Boolean ruleApplied;

	private String ruleType;

	private Boolean retrospectively;

	private String retrospectiveReason;

	private Boolean directlyShipped;

	private String directShipmentVerified;

	private String createdBy;

	private Long orgId;

}
