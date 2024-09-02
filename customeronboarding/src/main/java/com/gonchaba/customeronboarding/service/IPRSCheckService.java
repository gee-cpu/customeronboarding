package com.gonchaba.customeronboarding.service;

import com.gonchaba.customeronboarding.dto.CheckDTO;
import com.gonchaba.customeronboarding.dto.IprsResponseDTO;
import com.gonchaba.customeronboarding.enums.CheckStatus;
import com.gonchaba.customeronboarding.enums.Gender;
import com.gonchaba.customeronboarding.model.CustomerChecks;
import com.gonchaba.customeronboarding.model.CustomerDetails;
import com.gonchaba.customeronboarding.repository.CustomerChecksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("IPRSCheckService")
@RequiredArgsConstructor
public class IPRSCheckService implements CheckRunner {


    private final Map<String,IprsResponseDTO>iprsResponses = new HashMap<>();
    private final CustomerChecksRepository checksRepository;

    @Override
    public void runCheck(CheckDTO check) {
        if (iprsResponses.isEmpty()){
            iprsCheck();
        }
        Optional<CustomerChecks> optionalCustomerChecks = checksRepository.findById(check.getId());
        if (optionalCustomerChecks.isEmpty()){
            return;
        }
        CustomerChecks customerChecks = optionalCustomerChecks.get();
        CustomerDetails customerDetails = customerChecks.getCustomerDetails();

        IprsResponseDTO iprsResponseDTO = iprsResponses
                .getOrDefault(customerDetails.getIdNumber(),null);
        if (iprsResponseDTO==null){
            customerChecks.setCheckStatus(CheckStatus.FAILED);
            customerChecks.setMessage("unable to find information for this Id number");
        }
        else{
            if (iprsResponseDTO.getLastName().equals(customerDetails.getLastName())&&
                    iprsResponseDTO.getFirstName().equals(customerDetails.getFirstName()))
            {
                customerChecks.setCheckStatus(CheckStatus.PASSED);
                customerChecks.setMessage("IPRS check passed");

            }else {
                customerChecks.setCheckStatus(CheckStatus.FAILED);
                customerChecks.setMessage("Id did not match");
            }
        }
        checksRepository.save(customerChecks);

    }
    private void iprsCheck() {
        IprsResponseDTO iprsResponseDTO = IprsResponseDTO.builder()
                .placeOfIssue("Kebirigo")
                .sex(Gender.FEMALE)
                .districtOfBirth("Nairobi")
                .firstName("Kevina")
                .idNumber("37663443")
                .lastName("Nyota")
                .middleName("Boateng")
                .build();
        iprsResponses.put("37663443", iprsResponseDTO);

        IprsResponseDTO iprsResponseDTO2 = IprsResponseDTO.builder()
                .placeOfIssue("HDM")
                .sex(Gender.MALE)
                .districtOfBirth("Nairobi")
                .firstName("John")
                .idNumber("A245656555")
                .lastName("Kazungu")
                .middleName("ledama")
                .build();
        iprsResponses.put("A245656555", iprsResponseDTO2);

    }


}