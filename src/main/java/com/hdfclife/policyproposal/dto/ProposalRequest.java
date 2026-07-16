package com.hdfclife.policyproposal.dto;

import lombok.Data;

@Data
public class ProposalRequest {

    private String customerId;

    private Integer policyTerm;

    private Double sumAssured;

    private Double annualPremium;

    private String paymentFrequency;

    private String nomineeName;
}