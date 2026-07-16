package com.hdfclife.policyproposal.controller;

import com.hdfclife.policyproposal.service.ReferenceMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reference-master")
public class ReferenceMasterController {

    private final ReferenceMasterService referenceMasterService;

    public ReferenceMasterController(ReferenceMasterService referenceMasterService) {
        this.referenceMasterService = referenceMasterService;
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<?>> getReferenceData(@PathVariable("category") String category) {

        List<?> referenceData = referenceMasterService.getReferenceData(category);

        if (referenceData == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(referenceData);
    }
}