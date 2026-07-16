package com.hdfclife.policyproposal.repository;

import com.hdfclife.policyproposal.model.Proposal;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProposalRepository {

    private final Map<String, Proposal> proposalStore = new ConcurrentHashMap<>();

    public Proposal save(Proposal proposal) {
        proposalStore.put(proposal.getProposalId(), proposal);
        return proposal;
    }

    public Proposal findById(String proposalId) {
        return proposalStore.get(proposalId);
    }

    public Collection<Proposal> findAll() {
        return proposalStore.values();
    }

    public boolean existsById(String proposalId) {
        return proposalStore.containsKey(proposalId);
    }

}