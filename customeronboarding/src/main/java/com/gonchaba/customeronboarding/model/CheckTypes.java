package com.gonchaba.customeronboarding.model;


import com.gonchaba.customeronboarding.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="check_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Type type;
}