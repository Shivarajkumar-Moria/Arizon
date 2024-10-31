package com.arizon.service;

import java.util.List;
import java.util.Optional;

import com.arizon.dto.CustomerDTO;

public interface CustomerService {
    
    public Optional<CustomerDTO> insertCustomerDetails(CustomerDTO customerDTO);
    
    public Optional<CustomerDTO> fetchCustomerDetailsById(Long id);
    
    public List<CustomerDTO> fetchAllCustomersDetails();
    
    public Optional<CustomerDTO> updateCustomerDetails(Long id, CustomerDTO customerDTO);
    
    public void deleteCustomerDetails(Long id);
}