package com.hdfclife.policyproposal.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CustomerResponse {

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