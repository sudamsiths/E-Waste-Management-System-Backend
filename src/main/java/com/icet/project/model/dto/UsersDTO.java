package com.icet.project.model.dto;

import com.icet.project.utill.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDTO {
    private Long userId;
    private String fullName;
    private String contactNo;
    private String username;
    private String address;
    private String password;
    private String email;
    private String confirmPassword;
    private Role role;
}
