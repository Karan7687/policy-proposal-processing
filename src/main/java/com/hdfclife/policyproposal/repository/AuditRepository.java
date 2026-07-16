package com.hdfclife.policyproposal.repository;

import com.hdfclife.policyproposal.model.Audit;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuditRepository {

    private final Map<String, Audit> auditStore =
            new ConcurrentHashMap<>();

    public Audit save(Audit audit) {
        auditStore.put(audit.getAuditId(), audit);
        return audit;
    }

    public Collection<Audit> findAll() {
        return auditStore.values();
    }

}