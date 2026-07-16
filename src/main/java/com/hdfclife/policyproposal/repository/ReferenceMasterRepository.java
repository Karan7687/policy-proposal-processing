package com.hdfclife.policyproposal.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReferenceMasterRepository {

    private final Map<String, List<?>> referenceData = Map.of(
            "policy-term", List.of(10, 15, 20, 25, 30),
            "payment-frequency", List.of(
                    "MONTHLY",
                    "QUARTERLY",
                    "HALF_YEARLY",
                    "YEARLY")
    );
    public List<?> getReferenceData(String category) {
        return referenceData.get(category);
    }
}
