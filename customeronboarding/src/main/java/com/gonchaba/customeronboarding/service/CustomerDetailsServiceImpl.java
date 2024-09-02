package com.gonchaba.customeronboarding.service;

import com.gonchaba.customeronboarding.dto.CheckDTO;
import com.gonchaba.customeronboarding.enums.CheckStatus;
import com.gonchaba.customeronboarding.model.CheckTypes;
import com.gonchaba.customeronboarding.model.CustomerChecks;
import com.gonchaba.customeronboarding.model.CustomerDetails;
import com.gonchaba.customeronboarding.producer.Producer;
import com.gonchaba.customeronboarding.repository.CheckTypesRepository;
import com.gonchaba.customeronboarding.repository.CustomerDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerDetailsServiceImpl implements CustomerDetailsService {
    private final CustomerDetailsRepository customerRepository;
    private final CheckTypesRepository checkTypesRepository;
    private final Producer<CheckDTO> kraProducer;
    private final Producer<CheckDTO> iprsProducer;
    private final Producer<CheckDTO> crbProducer;

    public List<CustomerDetails> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<CustomerDetails> findCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

 public CustomerDetails saveCustomer(CustomerDetails customer) {
        customer.setStatus(CheckStatus.PENDING);

        List<CustomerChecks> customerChecks = checkTypesRepository.findAll().stream()
                .map(checkType -> {
                    CustomerChecks customerCheck = new CustomerChecks();
                    customerCheck.setCheckStatus(CheckStatus.PENDING);
                    customerCheck.setCustomerDetails(customer);
                    return customerCheck;
                })
                .collect(Collectors.toList());

        customer.setChecks(customerChecks);
        CustomerDetails savedCustomer = customerRepository.save(customer);

        addChecksToQueues(savedCustomer.getChecks());

        return savedCustomer;
    }


    @Async
    private void addChecksToQueues(List<CustomerChecks> checks) {
        checks.stream()
                .map(customerCheck -> new CheckDTO(customerCheck.getId(),
                        customerCheck.getCheckTypes().getType()))
                .forEach(checkDTO -> {
                    String checkType = String.valueOf(checkDTO.getType());

                    switch (checkType) {
                        case "CRB":
                            crbProducer.send(checkDTO);
                            break;
                        case "KRA":
                            kraProducer.send(checkDTO);
                            break;
                        case "IPRS":
                            iprsProducer.send(checkDTO);
                            break;
                        default:
                            break;
                    }
                });
    }




    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<CustomerDetails> updateCustomer(CustomerDetails customerDetails, UUID id) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setIdNumber(customerDetails.getIdNumber());
            existingCustomer.setKraPin(customerDetails.getKraPin());
            existingCustomer.setFirstName(customerDetails.getFirstName());
            existingCustomer.setLastName(customerDetails.getLastName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setStatus(customerDetails.getStatus());
            return customerRepository.save(existingCustomer);
        });
    }


}