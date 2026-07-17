package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.dto.ProposalRequest;
import com.hdfclife.policyproposal.dto.ProposalResponse;
import com.hdfclife.policyproposal.model.Proposal;
import com.hdfclife.policyproposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProposalService {

    //injction
    private final ProposalRepository proposalRepository;
    private final AuditService auditService;
    private static final AtomicInteger PROPOSAL_SEQUENCE =
            new AtomicInteger(1000);
    private static final AtomicInteger POLICY_SEQUENCE =
            new AtomicInteger(1000);
    /*
POL1001

POL1002

POL1003
    * */

    public ProposalService(ProposalRepository proposalRepository, AuditService auditService) {
        this.proposalRepository = proposalRepository;
        this.auditService = auditService;
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
    public ProposalResponse submitProposal(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null) {
            return null;
        }

        if ("SUBMITTED".equals(proposal.getStatus())) {
            return null;
        }

        proposal.setStatus("SUBMITTED");

        proposal.setPolicyNumber(
                "POL" + POLICY_SEQUENCE.incrementAndGet());

        proposalRepository.save(proposal);
        auditService.createAudit(
                proposal.getProposalId(),
                "Proposal Submitted"
        );

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
    public ProposalResponse getProposalById(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null) {
            return null;
        }

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