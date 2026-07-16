package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.repository.ReferenceMasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceMasterService {

    //dependency Injection
    private final ReferenceMasterRepository referenceMasterRepository;

    public ReferenceMasterService(ReferenceMasterRepository referenceMasterRepository) {
        this.referenceMasterRepository = referenceMasterRepository;
    }

    public List<?> getReferenceData(String category) {
        return referenceMasterRepository.getReferenceData(category);
    }
}