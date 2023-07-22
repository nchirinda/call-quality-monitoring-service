package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Customer;
import com.geneinsure.cqmservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    protected JpaRepository<Customer, String> getRepository() {
        return customerRepository;
    }
}
