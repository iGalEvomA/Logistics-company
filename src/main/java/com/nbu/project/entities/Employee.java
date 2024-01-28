package com.nbu.project.entities;


public record Employee (
    String email,
    String name,
    String type,
    int officeAddressId
){}
