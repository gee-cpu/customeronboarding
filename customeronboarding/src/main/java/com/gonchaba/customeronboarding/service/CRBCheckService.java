package com.gonchaba.customeronboarding.service;

import com.gonchaba.customeronboarding.dto.CheckDTO;
import com.gonchaba.customeronboarding.dto.CrbStatusResponseDTO;
import com.gonchaba.customeronboarding.enums.CheckStatus;
import com.gonchaba.customeronboarding.model.CustomerChecks;
import com.gonchaba.customeronboarding.model.CustomerDetails;
import com.gonchaba.customeronboarding.repository.CustomerChecksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("CRBCheckService")
@RequiredArgsConstructor
public class CRBCheckService implements CheckRunner{

    private final CustomerChecksRepository checksRepository;
    private final Map<String, CrbStatusResponseDTO> crbStatusResponseDTOMap = new HashMap<>();
    @Override
    public void runCheck(CheckDTO check) {
        if (crbStatusResponseDTOMap.isEmpty()){
            getCrbData();
        }
        Optional<CustomerChecks> optionalCustomerChecks = checksRepository.findById(check.getId());
        if (optionalCustomerChecks.isEmpty()){
            return;
        }
        CustomerChecks customerChecks = optionalCustomerChecks.get();
        CustomerDetails customerDetails = customerChecks.getCustomerDetails();

        CrbStatusResponseDTO crbStatusResponseDTO = crbStatusResponseDTOMap
                .getOrDefault(customerDetails.getIdNumber(),null);
        if (crbStatusResponseDTO==null){
            customerChecks.setCheckStatus(CheckStatus.FAILED);
            customerChecks.setMessage("unable to fetch a null id");

        }else{
            if (crbStatusResponseDTO.getIdNumber().equals(customerDetails.getIdNumber())){
                customerChecks.setCheckStatus(CheckStatus.PASSED);
            }else {
                customerChecks.setCheckStatus(CheckStatus.FAILED);
                customerChecks.setMessage("The id does not match");
            }
        }
         checksRepository.save(customerChecks);
    }
    private void getCrbData(){
        CrbStatusResponseDTO crbStatusResponseDTO = CrbStatusResponseDTO.builder()
                .dateChecked("21-12-2002")
                .firstName("Kevin")
                .lastName("Ochanda")
                .message("Status passed")
                .idNumber("2432243")
                .status(CheckStatus.PENDING)
                .build();
        crbStatusResponseDTOMap.put("2432243",crbStatusResponseDTO);

        CrbStatusResponseDTO crbStatusResponseDTO1 = CrbStatusResponseDTO.builder()
                .status(CheckStatus.PENDING)
                .idNumber("234344")
                .message("status is pending")
                .lastName("Onchaba")
                .firstName("George")
                .build();
        crbStatusResponseDTOMap.put("234344",crbStatusResponseDTO1);
    }
}
