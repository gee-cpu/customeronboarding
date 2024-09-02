package com.gonchaba.customeronboarding.repository;

import com.gonchaba.customeronboarding.model.CustomerChecks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerChecksRepository extends JpaRepository<CustomerChecks, UUID> {
}
