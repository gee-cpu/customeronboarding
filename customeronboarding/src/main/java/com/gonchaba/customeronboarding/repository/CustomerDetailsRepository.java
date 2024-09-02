package com.gonchaba.customeronboarding.repository;

import com.gonchaba.customeronboarding.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, UUID> {

    CustomerDetails findByIdNumber(String idNumber);


}
