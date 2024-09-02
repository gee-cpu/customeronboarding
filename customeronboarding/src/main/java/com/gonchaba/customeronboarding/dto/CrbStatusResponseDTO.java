package com.gonchaba.customeronboarding.dto;

import com.gonchaba.customeronboarding.enums.CheckStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrbStatusResponseDTO {
    private String dateChecked;
    private String firstName;
    private String lastName;
    private String message;
    private String idNumber;
    private CheckStatus status;

}
