package com.arizon.graphql;

import com.arizon.model.Customer;
import com.arizon.service.CustomerServiceGraphQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerResolver {

    @Autowired
    private CustomerServiceGraphQL customerServiceGraphQL;
    
    
    @MutationMapping
    public Customer insertCustomer(@Argument String customerName) {
    	System.out.print("HELLO");
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        return customerServiceGraphQL.insertCustomerDetails(customer).orElse(null);
    }
    
    @QueryMapping
    public Customer fetchCustomer(@Argument Long customerID) {
    	return customerServiceGraphQL.fetchCustomerDetailsById(customerID).orElse(null);
    }

}

/*
TO TEST IN POSTMAN

Method
POST


URL
localhost:8080/graphql


RequestBody - RAW - JSON -> Insert customer row
{
  "query": "mutation { insertCustomer(customerName: \"John Cena\") { customerID customerName } }"
}



RequestBody - RAW - JSON -> Fetches customer row using id
{
  "query": "query { fetchCustomer(customerID: \"1\") { customerID customerName } }"
}
*/


