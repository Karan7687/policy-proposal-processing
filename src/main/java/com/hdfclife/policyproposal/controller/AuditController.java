package com.hdfclife.policyproposal.controller;

import com.hdfclife.policyproposal.model.Audit;
import com.hdfclife.policyproposal.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<Collection<Audit>> getAllAudits() {
        return ResponseEntity.ok(auditService.getAllAudits());
    }
}
