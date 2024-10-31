package com.arizon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arizon.dao.CustomerRepository;
import com.arizon.model.Customer;

@Service
public class CustomerServiceGraphQLImpl implements CustomerServiceGraphQL{
	
    @Autowired
    private CustomerRepository repository;
	
	  //For GraphQL - from here
    public Optional<Customer> insertCustomerDetails(Customer customer) {
    	System.out.println(customer.getCustomerName());
        Customer savedCustomer = repository.save(customer);

        
        return Optional.of(savedCustomer);
    }
    
    
    public Optional<Customer> fetchCustomerDetailsById(Long id) {
        
        Optional<Customer> optionalCustomer = repository.findById(id);

        
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get(); 

            
            return Optional.of(customer);
        }

        return Optional.empty();
    }
  //For GraphQL - to here

}
