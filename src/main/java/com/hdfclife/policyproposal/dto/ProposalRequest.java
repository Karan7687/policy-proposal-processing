package com.hdfclife.policyproposal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProposalRequest {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Policy term is required")
    private Integer policyTerm;

    @NotNull(message = "Sum assured is required")
    @Min(value = 100000, message = "Sum assured must be at least 100000")
    private Double sumAssured;

    @NotNull(message = "Annual premium is required")
    @Min(value = 5000, message = "Annual premium must be at least 5000")
    private Double annualPremium;

    @NotBlank(message = "Payment frequency is required")
    private String paymentFrequency;

    @NotBlank(message = "Nominee name is required")
    private String nomineeName;
}