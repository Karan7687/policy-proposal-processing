package com.hdfclife.policyproposal.service;

import com.hdfclife.policyproposal.dto.CustomerRequest;
import com.hdfclife.policyproposal.dto.CustomerResponse;
import com.hdfclife.policyproposal.model.Customer;
import com.hdfclife.policyproposal.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private static final AtomicInteger CUSTOMER_SEQUENCE = new AtomicInteger(1000);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {

        String customerId = "CUST" + CUSTOMER_SEQUENCE.incrementAndGet();

        Customer customer = new Customer(
                customerId,
                request.getFirstName(),
                request.getLastName(),
                request.getAge(),
                request.getGender(),
                request.getEmail(),
                request.getMobileNumber(),
                request.getPanNumber(),
                request.getAddress()
        );

        customerRepository.save(customer);

        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAge(),
                customer.getGender(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getPanNumber(),
                customer.getAddress()
        );
    }
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerResponse(
                        customer.getCustomerId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getAge(),
                        customer.getGender(),
                        customer.getEmail(),
                        customer.getMobileNumber(),
                        customer.getPanNumber(),
                        customer.getAddress()
                ))
                .collect(Collectors.toList());
    }
    public CustomerResponse getCustomerById(String customerId) {

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            return null;
        }

        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAge(),
                customer.getGender(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getPanNumber(),
                customer.getAddress()
        );
    }
    public CustomerResponse updateCustomer(String customerId, CustomerRequest request) {
        Customer existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer == null) {
            return null;
        }
        existingCustomer.setFirstName(request.getFirstName());
        existingCustomer.setLastName(request.getLastName());
        existingCustomer.setAge(request.getAge());
        existingCustomer.setGender(request.getGender());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setMobileNumber(request.getMobileNumber());
        existingCustomer.setPanNumber(request.getPanNumber());
        existingCustomer.setAddress(request.getAddress());
        customerRepository.save(existingCustomer);
        return new CustomerResponse(
                existingCustomer.getCustomerId(),
                existingCustomer.getFirstName(),
                existingCustomer.getLastName(),
                existingCustomer.getAge(),
                existingCustomer.getGender(),
                existingCustomer.getEmail(),
                existingCustomer.getMobileNumber(),
                existingCustomer.getPanNumber(),
                existingCustomer.getAddress()
        );
    }
}