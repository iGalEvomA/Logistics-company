package com.nbu.project.dto;

import lombok.Data;

@Data
public class EmployeeRegisterRequest {
    private String email;
    private String name;
    private String type;
    private int officeId;
    private String password;
    private boolean isEmployee;
}
