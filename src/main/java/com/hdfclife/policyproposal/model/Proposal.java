package com.hdfclife.policyproposal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

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