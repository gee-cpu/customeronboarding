package com.gonchaba.customeronboarding.dto;

import com.gonchaba.customeronboarding.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckDTO {
    private UUID id;
    private Type type;
}
