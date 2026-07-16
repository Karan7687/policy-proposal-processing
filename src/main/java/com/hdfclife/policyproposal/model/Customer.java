package com.hdfclife.policyproposal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;
    private String mobileNumber;
    private String panNumber;
    private String address;

}