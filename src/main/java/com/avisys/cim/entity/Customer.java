package com.avisys.cim.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="customer")
	@JsonIgnore
	private List<MobileNumber> mobileNumberList;

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

	public List<MobileNumber> getMobileNumberList() {
		return mobileNumberList;
	}

	public void setMobileNumberList(List<MobileNumber> mobileNumberList) {
		this.mobileNumberList = mobileNumberList;
	}
}
