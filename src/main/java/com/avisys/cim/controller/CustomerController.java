package com.avisys.cim.controller;

import com.avisys.cim.model.CustomerModel;
import com.avisys.cim.response.CustomerResponse;
import com.avisys.cim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 2. Ability to create a new customer over REST API.
    // It should add new customer to DB, It only adds if the mobile number is not already present in DB.
    // As per requirement, returning 201 when customer created else 500 If already present
    @PostMapping("/addCustomers")
    public ResponseEntity<String> addNewCustomer(@RequestBody CustomerModel customerModel){
        CustomerModel response = customerServices.addCustomer(customerModel);
        if (response == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create Customer. Mobile number already present.");
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created");
    }

    // 5. Ability to delete over REST API
    // It should delete a customer by using mobile number.
    @DeleteMapping("/customers/{mobileNumber}")
    public ResponseEntity<String> deleteCustomerByMobileNumber(@PathVariable String mobileNumber) {
        if(customerServices.deleteCustomerByMobileNumber(mobileNumber)){
            return ResponseEntity.ok().body("Deleted");
        }
        return ResponseEntity.ok().body("number not exist");
    }
}
