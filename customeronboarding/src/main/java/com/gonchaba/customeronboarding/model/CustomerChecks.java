package com.gonchaba.customeronboarding.model;

import com.gonchaba.customeronboarding.enums.CheckStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer_checks")
public class CustomerChecks {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private CustomerDetails customerDetails;

    @ManyToOne
    @JoinColumn(name = "check_type_id", nullable = false)
    private CheckTypes checkTypes;

    @Enumerated(EnumType.STRING)
    private CheckStatus checkStatus;

    private String message;
}
