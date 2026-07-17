package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.dto.ProposalRequest;
import com.hdfclife.policyproposal.dto.ProposalResponse;
import com.hdfclife.policyproposal.model.Proposal;
import com.hdfclife.policyproposal.repository.CustomerRepository;
import com.hdfclife.policyproposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final CustomerRepository customerRepository;
    private final ReferenceMasterService referenceMasterService;
    private final AuditService auditService;

    private static final AtomicInteger PROPOSAL_SEQUENCE =
            new AtomicInteger(1000);

    private static final AtomicInteger POLICY_SEQUENCE =
            new AtomicInteger(1000);

    public ProposalService(ProposalRepository proposalRepository,
                           CustomerRepository customerRepository,
                           ReferenceMasterService referenceMasterService,
                           AuditService auditService) {

        this.proposalRepository = proposalRepository;
        this.customerRepository = customerRepository;
        this.referenceMasterService = referenceMasterService;
        this.auditService = auditService;
    }

    public ProposalResponse createProposal(ProposalRequest request) {

        System.out.println("Customer ID Received: " + request.getCustomerId());
        System.out.println("Customer Exists: " + customerRepository.existsById(request.getCustomerId()));
        // Business Validation 1 - Customer must exist
        if (!customerRepository.existsById(request.getCustomerId())) {
            return null;
        }

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