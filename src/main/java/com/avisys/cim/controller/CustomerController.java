package com.avisys.cim.controller;

import com.avisys.cim.response.CustomerResponse;
import com.avisys.cim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerServices;

    // 1. Get Customer information over an REST API call
    // It should be return All Customers related information in JSON format.
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        List<CustomerResponse> customerList =  customerServices.getCustomers();
        if (customerList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(customerList);
    }

    // 1. Get Customer information over an REST API call
    // It should return Filtered Customers related information in JSON format.
    @GetMapping("/filteredCustomers")
    public ResponseEntity<List<CustomerResponse>> getCustomersByFilter(@RequestParam(value = "firstName", required = false) String firstName,
                                                                       @RequestParam(value = "lastName", required = false) String lastName,
                                                                       @RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
        List<CustomerResponse> customerFilterList =  customerServices.getFilterCustomers(firstName, lastName, mobileNumber);
        if (customerFilterList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(customerFilterList);
    }

}
