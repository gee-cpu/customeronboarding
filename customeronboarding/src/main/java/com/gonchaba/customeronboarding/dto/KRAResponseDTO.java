package com.gonchaba.customeronboarding.dto;

import com.gonchaba.customeronboarding.enums.CheckStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KRAResponseDTO {
    private String pin;
    private String idNumber;
    private String dateRegistered;
    private String occupation;
    private CheckStatus status;
}
