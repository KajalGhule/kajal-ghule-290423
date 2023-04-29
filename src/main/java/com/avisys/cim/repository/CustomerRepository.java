package com.avisys.cim.repository;

import com.avisys.cim.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c")
    List<Customer> getAllCustomers();

    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
    List<Customer> getCustomersByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Customer> getCustomersByLastName(String lastName);

    @Query("SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber")
    List<Customer> getCustomersByMobileNumber(String mobileNumber);

}
