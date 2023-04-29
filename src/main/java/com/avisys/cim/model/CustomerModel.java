package com.avisys.cim.model;

import java.util.List;

public class CustomerModel {

    private Long id;
    private String firstName;
    private String lastName;
    private List<String> mobileNumbers;

    public CustomerModel() {}

    public CustomerModel(Long id, String firstName, String lastName, List<String> mobileNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumbers = mobileNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }
}
