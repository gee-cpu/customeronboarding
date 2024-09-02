package com.gonchaba.customeronboarding.repository;

import com.gonchaba.customeronboarding.model.CheckTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckTypesRepository extends JpaRepository<CheckTypes, UUID> {
}
