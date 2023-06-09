package com.avisys.cim.service;

import com.avisys.cim.entity.Customer;
import com.avisys.cim.entity.MobileNumber;
import com.avisys.cim.model.CustomerModel;
import com.avisys.cim.repository.CustomerRepository;
import com.avisys.cim.repository.MobileNumberRepository;
import com.avisys.cim.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    public List<CustomerResponse> getCustomers() {
        List<Customer> customerList = customerRepository.getAllCustomers();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerList.forEach(customer -> {
            List<String> mobileNumberList = new ArrayList<>();
            customer.getMobileNumberList().forEach(mobileNumber -> {
                mobileNumberList.add(mobileNumber.getMobileNumber());
            });
            customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(),
                    customer.getLastName(), mobileNumberList));
        });
        return customerResponseList;
    }

    public List<CustomerResponse> getFilterCustomers(String firstName, String lastName, String mobileNumber) {
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        List<Customer> customers;
        List<String> mobileNumberList = new ArrayList<>();
        if (firstName != null && !firstName.isEmpty()) {
            customers = customerRepository.getCustomersByFirstName(firstName);
            customers.forEach(customer -> {
                customer.getMobileNumberList().forEach(mobileNum -> mobileNumberList.add(mobileNum.getMobileNumber()));
                customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), mobileNumberList));
            });
        }
        if (lastName != null && !lastName.isEmpty()) {
            customers = customerRepository.getCustomersByLastName(lastName);
            customers.forEach(customer -> {
                customer.getMobileNumberList().forEach(mobileNum -> mobileNumberList.add(mobileNum.getMobileNumber()));
                customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), mobileNumberList));
            });
        }
        if (mobileNumber != null && !mobileNumber.isEmpty()) {
            MobileNumber mobileNumberWithCustomerDetails = mobileNumberRepository.getMobileNumberDetails(mobileNumber);
            if (mobileNumberWithCustomerDetails != null) {
                customers = new ArrayList<>();
                customers.add(mobileNumberWithCustomerDetails.getCustomer());
                customers.forEach(customer -> {
                    customer.getMobileNumberList().forEach(mobileNum -> mobileNumberList.add(mobileNum.getMobileNumber()));
                    customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), mobileNumberList));
                });
            }
        }
        return customerResponseList;
    }

    public CustomerModel addCustomer(CustomerModel customerModel) {
        if (customerModel != null) {
            List<String> alreadyExistingMobileNumberList = new ArrayList<>();
            mobileNumberRepository.findAll().forEach(mn -> alreadyExistingMobileNumberList.add(mn.getMobileNumber()));

            List<String> commonMobileNumberList = new ArrayList<>();
            alreadyExistingMobileNumberList.forEach(alreadyExistMobileNumber -> {
                customerModel.getMobileNumbers().forEach(mobileNumberToBeAdd -> {
                    if (alreadyExistMobileNumber.equals(mobileNumberToBeAdd)) {
                        commonMobileNumberList.add(mobileNumberToBeAdd);
                    }
                });
            });

            if (commonMobileNumberList.isEmpty()) {
                List<MobileNumber> mobileNumberListToAdd = new ArrayList<>();
                Customer customer = new Customer();
                customer.setFirstName(customerModel.getFirstName());
                customer.setLastName(customerModel.getLastName());
                customerModel.getMobileNumbers().forEach(mobNo -> {
                    MobileNumber mobileNumber = new MobileNumber();
                    mobileNumber.setMobileNumber(mobNo);
                    mobileNumberListToAdd.add(mobileNumber);
                });
                Customer savedCustomer = customerRepository.save(customer);
                mobileNumberListToAdd.forEach(mobileNumber -> {
                    mobileNumber.setCustomer(savedCustomer);
                });
                mobileNumberRepository.saveAll(mobileNumberListToAdd);
                return customerModel;
            }
        }
        return null;
    }

    public boolean deleteCustomerByMobileNumber(String mobileNumber) {
        MobileNumber mobileNumberEntity = mobileNumberRepository.getMobileNumberDetails(mobileNumber);
        if (mobileNumberEntity != null) {
            customerRepository.delete(mobileNumberEntity.getCustomer());
            return true;
        }
        return false;
    }

    public CustomerResponse addMobileNumberToCustomer(Long customerId, String mobileNumber) {
        if (customerId != null && mobileNumber != null) {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            Customer customerById;
            if (customerOptional.isPresent()) {
                customerById = customerOptional.get();

                MobileNumber existingMobileNumber = mobileNumberRepository.getMobileNumberDetails(mobileNumber);
                if (existingMobileNumber == null) {
                    MobileNumber mobileNumberToBeSave = new MobileNumber();
                    mobileNumberToBeSave.setMobileNumber(mobileNumber);
                    mobileNumberToBeSave.setCustomer(customerById);
                    mobileNumberRepository.save(mobileNumberToBeSave);

                    List<String> updatedMobileNumberList = new ArrayList<>();
                    customerById.getMobileNumberList().forEach(item -> {
                        updatedMobileNumberList.add(item.getMobileNumber());
                    });
                    updatedMobileNumberList.add(mobileNumber);

                    return new CustomerResponse(customerById.getId(), customerById.getFirstName(),
                            customerById.getLastName(), updatedMobileNumberList);
                }
            }
        }
        return null;
    }

    public boolean deleteCustomersMobileNumber(Long customerId, String mobileNumber) {
        MobileNumber existingMobileNumber = mobileNumberRepository.getMobileNumberDetails(mobileNumber);
        if (existingMobileNumber != null && existingMobileNumber.getCustomer().getId().equals(customerId)) {
            mobileNumberRepository.delete(existingMobileNumber);
            return true;
        }
        return false;
    }
}
