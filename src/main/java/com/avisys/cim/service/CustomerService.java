package com.avisys.cim.service;

import com.avisys.cim.entity.Customer;
import com.avisys.cim.repository.CustomerRepository;
import com.avisys.cim.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerResponse> getCustomers() {
        List<Customer> customerList = customerRepository.getAllCustomers();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerList.forEach(customer -> {
            customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getMobileNumber()));
        });
        return customerResponseList;
    }

    public List<CustomerResponse> getFilterCustomers(String firstName, String lastName, String mobileNumber) {
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        List<Customer> customers;
        if (firstName != null && !firstName.isEmpty()) {
            customers = customerRepository.getCustomersByFirstName(firstName);
            customers.forEach(customer -> {
                customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getMobileNumber()));
            });
        }
        if (lastName != null && !lastName.isEmpty()) {
            customers = customerRepository.getCustomersByLastName(lastName);
            customers.forEach(customer -> {
                customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getMobileNumber()));
            });
        }
        if (mobileNumber != null && !mobileNumber.isEmpty()) {
            customers = customerRepository.getCustomersByMobileNumber(mobileNumber);
            customers.forEach(customer -> {
                customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getMobileNumber()));
            });
        }
        return customerResponseList;
    }


}
