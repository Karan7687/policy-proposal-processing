package com.hdfclife.policyproposal.repository;


import com.hdfclife.policyproposal.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {

    private final Map<String, Customer> customerStore = new ConcurrentHashMap<>();
/*Key                Value

CUST1001  ─────► Customer Object

CUST1002  ─────► Customer Object

CUST1003  ─────► Customer Object

*/
    public Customer save(Customer customer) {
        customerStore.put(customer.getCustomerId(), customer);
        return customer;
    }

    public Customer findById(String customerId) {
        return customerStore.get(customerId);
    }

    public Collection<Customer> findAll() {
        return customerStore.values();
    }

    public boolean existsById(String customerId) {
        return customerStore.containsKey(customerId);
    }

}