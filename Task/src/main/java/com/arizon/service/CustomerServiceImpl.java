package com.arizon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arizon.dao.CustomerRepository;
import com.arizon.dto.CustomerDTO;
import com.arizon.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;
    

    @Override
    public Optional<CustomerDTO> insertCustomerDetails(CustomerDTO customerDTO) {
    	System.out.println(customerDTO.getCustomerName());
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        
        System.out.println(customerDTO.getCustomerName());
        Customer savedCustomer = repository.save(customer);

        
        CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setCustomerID(savedCustomer.getCustomerID());
        savedCustomerDTO.setCustomerName(savedCustomer.getCustomerName());
        
        return Optional.of(savedCustomerDTO);
    }


    @Override
    public Optional<CustomerDTO> fetchCustomerDetailsById(Long id) {
        
        Optional<Customer> optionalCustomer = repository.findById(id);

        
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get(); 

            
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(customer.getCustomerID());
            customerDTO.setCustomerName(customer.getCustomerName());

            
            return Optional.of(customerDTO);
        }

        return Optional.empty();
    }

    @Override
    public List<CustomerDTO> fetchAllCustomersDetails() {
        
        List<Customer> customers = repository.findAll();

        
        return customers.stream().map(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(customer.getCustomerID());
            customerDTO.setCustomerName(customer.getCustomerName());
            return customerDTO;
        }).toList(); 
    }

    @Override
    public Optional<CustomerDTO> updateCustomerDetails(Long id, CustomerDTO customerDTO) {
        return repository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setCustomerID(customerDTO.getCustomerID());
                    existingCustomer.setCustomerName(customerDTO.getCustomerName());
                    repository.save(existingCustomer); 
                    return customerDTO; 
                })
                .or(() -> Optional.empty()); 
    }

    @Override
    public void deleteCustomerDetails(Long id) {
        repository.deleteById(id); 
    }
}
