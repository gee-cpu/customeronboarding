package com.gonchaba.customeronboarding.dto;

import com.gonchaba.customeronboarding.enums.CheckStatus;
import com.gonchaba.customeronboarding.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IprsResponseDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String serialNumber;
    private String idNumber;
    private Gender sex;
    private String districtOfBirth;
    private String placeOfIssue;
    private CheckStatus status;

}
