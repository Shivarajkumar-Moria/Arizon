package com.arizon.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arizon.dto.CustomerDTO;
import com.arizon.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/access/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> insertCustomerDetails(@RequestBody CustomerDTO customerDTO) {
        Optional<CustomerDTO> createdCustomer = customerService.insertCustomerDetails(customerDTO);
        return createdCustomer.map(customer -> ResponseEntity.status(201).body(customer)) // 201 - Successful inserted row
                              .orElseGet(() -> ResponseEntity.badRequest().build()); // 400 - Failed
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> fetchCustomerDetailsById(@PathVariable Long id) {
        Optional<CustomerDTO> customerDTO = customerService.fetchCustomerDetailsById(id);
        
        return customerDTO.map(ResponseEntity::ok) // 200 - Successfully fetched customer details
                          .orElseGet(() -> ResponseEntity.notFound().build()); //404 - Unable to fetch
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> fetchAllCustomersDetails() {
        List<CustomerDTO> customers = customerService.fetchAllCustomersDetails();
        return ResponseEntity.ok(customers); // 200 - Successfully fetched customers details
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerDetails(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Optional<CustomerDTO> updatedCustomer = customerService.updateCustomerDetails(id, customerDTO);
        
        return updatedCustomer.map(ResponseEntity::ok) //200 - Successfully updated customer details
                              .orElseGet(() -> ResponseEntity.notFound().build()); //404 - Unable to update
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerDetails(@PathVariable Long id) {
        customerService.deleteCustomerDetails(id);
        return ResponseEntity.noContent().build(); //204 - Successfully deleted customer details (and sending no content)
    }
}
