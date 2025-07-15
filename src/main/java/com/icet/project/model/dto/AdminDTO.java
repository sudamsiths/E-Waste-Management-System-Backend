package com.icet.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDTO {
    private Long adminId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String contactNo;
}
