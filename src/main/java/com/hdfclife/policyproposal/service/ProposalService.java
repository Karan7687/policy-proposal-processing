package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.dto.ProposalRequest;
import com.hdfclife.policyproposal.dto.ProposalResponse;
import com.hdfclife.policyproposal.exception.BusinessValidationException;
import com.hdfclife.policyproposal.exception.ResourceNotFoundException;
import com.hdfclife.policyproposal.model.Customer;
import com.hdfclife.policyproposal.model.Proposal;
import com.hdfclife.policyproposal.repository.CustomerRepository;
import com.hdfclife.policyproposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

        // Customer must exist
        if (!customerRepository.existsById(request.getCustomerId())) {
            throw new ResourceNotFoundException(
                    "Customer not found with ID: " + request.getCustomerId()
            );
        }

        Customer customer = customerRepository.findById(request.getCustomerId());

        // Policy Term Validation
        List<?> validTerms = referenceMasterService.getReferenceData("policy-term");
        if (!validTerms.contains(request.getPolicyTerm())) {
            throw new BusinessValidationException("Invalid policy term.");
        }

        // Payment Frequency Validation
        List<?> validFrequencies =
                referenceMasterService.getReferenceData("payment-frequency");

        if (!validFrequencies.contains(request.getPaymentFrequency())) {
            throw new BusinessValidationException("Invalid payment frequency.");
        }

        // PAN Validation
        if (request.getAnnualPremium() > 50000 &&
                (customer.getPanNumber() == null ||
                        customer.getPanNumber().isBlank())) {

            throw new BusinessValidationException(
                    "PAN is mandatory when annual premium exceeds 50000."
            );
        }

        // Nominee Validation
        String customerName =
                customer.getFirstName() + " " + customer.getLastName();

        if (customerName.equalsIgnoreCase(request.getNomineeName())) {
            throw new BusinessValidationException(
                    "Nominee cannot be the customer."
            );
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
            throw new ResourceNotFoundException(
                    "Proposal not found with ID: " + proposalId
            );
        }

        if ("SUBMITTED".equals(proposal.getStatus())) {
            throw new BusinessValidationException(
                    "Proposal has already been submitted."
            );
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
            throw new ResourceNotFoundException(
                    "Proposal not found with ID: " + proposalId
            );
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