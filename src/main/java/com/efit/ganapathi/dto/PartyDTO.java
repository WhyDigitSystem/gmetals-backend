package com.efit.ganapathi.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyDTO {

    private Long id;

    @NotBlank(message = "Party Name is required")
    private String partyName;

    @NotBlank(message = "Party Code is required")
    private String partyCode;

    private String contactPerson;

    @NotBlank(message = "Party Type is required")
    private String partyType;

    @NotBlank(message = "Phone Number is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    private BigDecimal creditLimit;

    // common fields
    private String branch;

    private String branchCode;

    private boolean active;

    @NotNull(message = "Org ID is required")
    private Long orgId;

    @NotBlank(message = "CreatedBy is required")
    private String createdBy;
}
