package com.gonchaba.customeronboarding.service;

import com.gonchaba.customeronboarding.model.CustomerDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDetailsService {

    Optional<CustomerDetails> findCustomerById(UUID id);

    List<CustomerDetails> findAllCustomers();

    CustomerDetails saveCustomer(CustomerDetails customer);

    void deleteCustomer(UUID id);

    Optional<CustomerDetails> updateCustomer(CustomerDetails customerDetails,UUID uuid);
}
