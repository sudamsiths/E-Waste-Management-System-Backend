package com.icet.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private Long userId;
    private String fullName;
    private String contactNo;
    private String username;
    private String address;
    private String password;
    private String email;
    private String confirmPassword;
}
