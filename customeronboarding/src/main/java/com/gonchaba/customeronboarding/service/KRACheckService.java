package com.gonchaba.customeronboarding.service;

import com.gonchaba.customeronboarding.dto.CheckDTO;
import com.gonchaba.customeronboarding.dto.KRAResponseDTO;
import com.gonchaba.customeronboarding.enums.CheckStatus;
import com.gonchaba.customeronboarding.model.CustomerChecks;
import com.gonchaba.customeronboarding.model.CustomerDetails;
import com.gonchaba.customeronboarding.repository.CustomerChecksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("kraCheckService")
@RequiredArgsConstructor
public class KRACheckService implements CheckRunner {
    private final Map<String, KRAResponseDTO> pins = new HashMap<>();
    private final CustomerChecksRepository checksRepository;

    @Override
    public void runCheck(CheckDTO check) {
        if (pins.isEmpty()) {
            seedPins();
        }
        Optional<CustomerChecks> optionalCustomerChecks = checksRepository.findById(check.getId());
        if (optionalCustomerChecks.isEmpty()) {
            return;
        }
        CustomerChecks customerCheck = optionalCustomerChecks.get();
        CustomerDetails customerDetails = customerCheck.getCustomerDetails();
        KRAResponseDTO kraResponseDTO = pins.getOrDefault(customerDetails.getIdNumber(), null);
        if (kraResponseDTO == null) {
            customerCheck.setCheckStatus(CheckStatus.FAILED);
            customerCheck.setMessage("Unable to find Pin for this Id Number");
        } else {
            if (kraResponseDTO.getPin().equals(customerDetails.getKraPin())) {
                customerCheck.setCheckStatus(CheckStatus.PASSED);
            } else {
                customerCheck.setCheckStatus(CheckStatus.FAILED);
                customerCheck.setMessage("KRA Pin provided did not match with Id Number");
            }
        }
        checksRepository.save(customerCheck);
    }

    private void seedPins() {
        KRAResponseDTO pin1 = KRAResponseDTO.builder()
                .pin("A932323230")
                .dateRegistered("2012-05-12")
                .idNumber("123456")
                .occupation("IT")
                .build();
        pins.put("123456", pin1);

        KRAResponseDTO pin2 = KRAResponseDTO.builder()
                .pin("A980343434")
                .dateRegistered("2002-10-04")
                .idNumber("987654")
                .occupation("Business")
                .build();
        pins.put("987654", pin2);
    }
}
