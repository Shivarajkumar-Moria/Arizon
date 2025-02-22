package com.arizon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arizon.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
