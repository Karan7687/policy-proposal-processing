package com.hdfclife.policyproposal.dto;

import lombok.Data;

@Data
public class CustomerRequest {

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String email;

    private String mobileNumber;

    private String panNumber;

    private String address;

}