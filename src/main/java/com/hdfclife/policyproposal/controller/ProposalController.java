package com.hdfclife.policyproposal.controller;

import com.hdfclife.policyproposal.dto.ProposalRequest;
import com.hdfclife.policyproposal.dto.ProposalResponse;
import com.hdfclife.policyproposal.service.ProposalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    public ResponseEntity<ProposalResponse> createProposal(
            @Valid @RequestBody ProposalRequest request) {

        ProposalResponse response = proposalService.createProposal(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{proposalId}")
    public ResponseEntity<ProposalResponse> getProposalById(
            @PathVariable("proposalId") String proposalId) {

        ProposalResponse response = proposalService.getProposalById(proposalId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{proposalId}/submit")
    public ResponseEntity<ProposalResponse> submitProposal(
            @PathVariable("proposalId") String proposalId) {

        ProposalResponse response =
                proposalService.submitProposal(proposalId);

        return ResponseEntity.ok(response);
    }
}