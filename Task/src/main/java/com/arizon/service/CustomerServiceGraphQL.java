package com.arizon.service;

import java.util.Optional;

import com.arizon.model.Customer;

public interface CustomerServiceGraphQL {
	
	public Optional<Customer> insertCustomerDetails(Customer customer);
	
	 public Optional<Customer> fetchCustomerDetailsById(Long id);
	
}