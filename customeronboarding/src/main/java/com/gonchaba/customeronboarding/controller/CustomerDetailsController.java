package com.gonchaba.customeronboarding.controller;

import com.gonchaba.customeronboarding.model.CustomerDetails;
import com.gonchaba.customeronboarding.service.CustomerDetailsService;
import com.gonchaba.customeronboarding.service.CustomerDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerDetailsController {
    private final CustomerDetailsService customerService;

    @GetMapping
    public List<CustomerDetails> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetails> getCustomerById(@PathVariable UUID customerId) {
        Optional<CustomerDetails> customer = customerService.findCustomerById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CustomerDetails createCustomer(@RequestBody CustomerDetails customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDetails> updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDetails customerDetails) {
        Optional<CustomerDetails> updatedCustomer = customerService.updateCustomer(customerDetails, customerId);
        return updatedCustomer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        Optional<CustomerDetails> customer = customerService.findCustomerById(customerId);
        if (customer.isPresent()) {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
