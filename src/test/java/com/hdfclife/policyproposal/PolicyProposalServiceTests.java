package com.hdfclife.policyproposal;

import com.hdfclife.policyproposal.dto.CustomerRequest;
import com.hdfclife.policyproposal.dto.ProposalRequest;
import com.hdfclife.policyproposal.model.Customer;
import com.hdfclife.policyproposal.repository.AuditRepository;
import com.hdfclife.policyproposal.repository.CustomerRepository;
import com.hdfclife.policyproposal.repository.ProposalRepository;
import com.hdfclife.policyproposal.repository.ReferenceMasterRepository;
import com.hdfclife.policyproposal.service.AuditService;
import com.hdfclife.policyproposal.service.ProposalService;
import com.hdfclife.policyproposal.service.ReferenceMasterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PolicyProposalServiceTests {

    private Validator validator;
    private ProposalService proposalService;
    private CustomerRepository customerRepository;
    private ProposalRepository proposalRepository;
    private AuditService auditService;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        customerRepository = new CustomerRepository();
        proposalRepository = new ProposalRepository();
        ReferenceMasterRepository referenceMasterRepository = new ReferenceMasterRepository();
        ReferenceMasterService referenceMasterService = new ReferenceMasterService(referenceMasterRepository);
        auditService = new AuditService(new AuditRepository());
        proposalService = new ProposalService(proposalRepository, auditService, customerRepository,
                referenceMasterService);
    }

    @Test
    void customerValidationShouldRejectInvalidAge() {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName("Test");
        request.setLastName("User");
        request.setAge(17);
        request.setGender("Male");
        request.setEmail("test@example.com");
        request.setMobileNumber("9876543210");
        request.setAddress("Pune");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Age must be at least 18")));
    }

    @Test
    void proposalValidationShouldRejectUnsupportedPolicyTerm() {
        Customer existingCustomer = new Customer("CUST1001", "Test", "User", 30, "Male", "test@example.com",
                "9876543210", "ABCDE1234F", "Pune");
        customerRepository.save(existingCustomer);

        ProposalRequest request = new ProposalRequest();
        request.setCustomerId("CUST1001");
        request.setPolicyTerm(12);
        request.setSumAssured(150000.0);
        request.setAnnualPremium(20000.0);
        request.setPaymentFrequency("YEARLY");
        request.setNomineeName("Spouse");

        BusinessValidationException exception = assertThrows(BusinessValidationException.class,
                () -> proposalService.createProposal(request));

        assertEquals("Policy term must be one of 10, 15, 20, 25, 30", exception.getMessage());
    }

    @Test
    void proposalSubmissionSuccessShouldGeneratePolicyNumberAndAudit() {
        Customer existingCustomer = new Customer("CUST1001", "Test", "User", 30, "Male", "test@example.com",
                "9876543210", "ABCDE1234F", "Pune");
        customerRepository.save(existingCustomer);

        ProposalRequest request = new ProposalRequest();
        request.setCustomerId("CUST1001");
        request.setPolicyTerm(20);
        request.setSumAssured(1000000.0);
        request.setAnnualPremium(25000.0);
        request.setPaymentFrequency("YEARLY");
        request.setNomineeName("Spouse");

        var created = proposalService.createProposal(request);
        assertEquals("DRAFT", created.getStatus());
        assertNull(created.getPolicyNumber());

        var submitted = proposalService.submitProposal(created.getProposalId());
        assertEquals("SUBMITTED", submitted.getStatus());
        assertNotNull(submitted.getPolicyNumber());
        assertTrue(submitted.getPolicyNumber().startsWith("POL"));
        assertEquals(1, auditService.getAllAudits().size());
    }

    @Test
    void proposalSubmissionFailureShouldRejectDuplicateSubmission() {
        Customer existingCustomer = new Customer("CUST1001", "Test", "User", 30, "Male", "test@example.com",
                "9876543210", "ABCDE1234F", "Pune");
        customerRepository.save(existingCustomer);

        ProposalRequest request = new ProposalRequest();
        request.setCustomerId("CUST1001");
        request.setPolicyTerm(20);
        request.setSumAssured(1000000.0);
        request.setAnnualPremium(25000.0);
        request.setPaymentFrequency("YEARLY");
        request.setNomineeName("Spouse");

        var created = proposalService.createProposal(request);
        proposalService.submitProposal(created.getProposalId());

        BusinessValidationException exception = assertThrows(BusinessValidationException.class,
                () -> proposalService.submitProposal(created.getProposalId()));

        assertEquals("Proposal has already been submitted", exception.getMessage());
        assertEquals(1, auditService.getAllAudits().size());
    }

    @Test
    void referenceMasterShouldReturnPolicyTermValues() {
        ReferenceMasterRepository repository = new ReferenceMasterRepository();
        ReferenceMasterService referenceMasterService = new ReferenceMasterService(repository);

        var values = referenceMasterService.getReferenceData("policy-term");

        assertEquals(5, values.size());
        assertTrue(values.contains(10));
        assertTrue(values.contains(15));
        assertTrue(values.contains(20));
        assertTrue(values.contains(25));
        assertTrue(values.contains(30));
    }

    @Test
    void referenceMasterShouldThrowWhenCategoryNotFound() {
        ReferenceMasterRepository repository = new ReferenceMasterRepository();
        ReferenceMasterService referenceMasterService = new ReferenceMasterService(repository);

        assertThrows(ResourceNotFoundException.class,
                () -> referenceMasterService.getReferenceData("unknown-category"));
    }
}
