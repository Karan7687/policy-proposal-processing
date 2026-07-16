package com.hdfclife.policyproposal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProposalResponse {

    private String proposalId;

    private String customerId;

    private Integer policyTerm;

    private Double sumAssured;

    private Double annualPremium;

    private String paymentFrequency;

    private String nomineeName;

    private String status;

    private String policyNumber;
}