package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.dto.ProposalRequest;
import com.hdfclife.policyproposal.dto.ProposalResponse;
import com.hdfclife.policyproposal.model.Proposal;
import com.hdfclife.policyproposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    private static final AtomicInteger PROPOSAL_SEQUENCE =
            new AtomicInteger(1000);

    public ProposalService(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public ProposalResponse createProposal(ProposalRequest request) {

        String proposalId = "PROP" + PROPOSAL_SEQUENCE.incrementAndGet();

        Proposal proposal = new Proposal(
                proposalId,
                request.getCustomerId(),
                request.getPolicyTerm(),
                request.getSumAssured(),
                request.getAnnualPremium(),
                request.getPaymentFrequency(),
                request.getNomineeName(),
                "DRAFT",
                null
        );

        proposalRepository.save(proposal);

        return new ProposalResponse(
                proposal.getProposalId(),
                proposal.getCustomerId(),
                proposal.getPolicyTerm(),
                proposal.getSumAssured(),
                proposal.getAnnualPremium(),
                proposal.getPaymentFrequency(),
                proposal.getNomineeName(),
                proposal.getStatus(),
                proposal.getPolicyNumber()
        );
    }
}