package com.avisys.cim.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MOBILE_NUMBER")
public class MobileNumber {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "MOBILE_NUMBER", unique = true, nullable = false)
	private String mobileNumber;

	@ManyToOne
	@JoinTable(name="mobile_number_customer", joinColumns={ @JoinColumn(name = "mobile_number_id") },
			inverseJoinColumns = { @JoinColumn(name = "customer_id" ) } )
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
