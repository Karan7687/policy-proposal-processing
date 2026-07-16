package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.model.Audit;
import com.hdfclife.policyproposal.repository.AuditRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    private static final AtomicInteger AUDIT_SEQUENCE =
            new AtomicInteger(1000);

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void createAudit(String proposalId, String action) {

        Audit audit = new Audit(
                "AUD" + AUDIT_SEQUENCE.incrementAndGet(),
                proposalId,
                action,
                LocalDateTime.now()
        );

        auditRepository.save(audit);
    }

    public Collection<Audit> getAllAudits() {
        return auditRepository.findAll();
    }
}