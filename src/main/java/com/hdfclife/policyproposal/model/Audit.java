package com.hdfclife.policyproposal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    private String auditId;

    private String proposalId;

    private String action;

    private LocalDateTime timestamp;

}