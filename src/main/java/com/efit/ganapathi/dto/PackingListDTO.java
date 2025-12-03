package com.efit.ganapathi.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingListDTO {
	private Long id;

    @NotBlank(message = "Please enter Exporter Name")
    private String exporter;

    @NotBlank(message = "Please enter Importer Name")
    private String importer;

    @NotBlank(message = "Please enter Packing List Number")
    private String packingListNumber;

    @NotNull(message = "Packing Date is required")
    private LocalDate packingDate;

    @NotBlank(message = "Please enter Packing Type")
    private String packingType;

    @NotBlank(message = "Please enter Branch Code")
    private String branchCode;

    @NotNull(message = "Org Id is required")
    private Long orgId;

    @NotBlank(message = "Created By is required")
    private String createdBy;

	private List<PackingListDetailsDTO> packingListDetailsDTO;

}
