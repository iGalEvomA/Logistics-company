package com.nbu.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@AllArgsConstructor
public class Employee {

    @Id
    @NotNull
    @Email
    private String email;

    private String token1;

    private String token2;

    private String type; // Employee or Manager

    @ManyToOne
    @JoinColumn(name = "office_address_id", nullable = false)
    private Address officeAddress;
}
