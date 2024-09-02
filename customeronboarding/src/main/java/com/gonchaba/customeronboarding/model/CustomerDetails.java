package com.gonchaba.customeronboarding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gonchaba.customeronboarding.enums.CheckStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "kra_pin")
    private String kraPin;
    @Column(name="first_name",nullable=false)
    private String firstName;
    @Column(name="last_name",nullable=false)
    private String lastName;
    @Column(name="email",nullable=false,unique=true)
    private String email;
    @Column(name="phone_number",nullable = false,unique = true)
    private String phoneNumber;
    private CheckStatus status;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerChecks> checks;


}
