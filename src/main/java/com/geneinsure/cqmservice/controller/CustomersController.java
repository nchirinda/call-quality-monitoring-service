package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.Customer;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "customers")
public class CustomersController extends AbstractController<Customer> {

    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomersController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    protected AbstractService<Customer> getService() {
        return customerService;
    }
}
