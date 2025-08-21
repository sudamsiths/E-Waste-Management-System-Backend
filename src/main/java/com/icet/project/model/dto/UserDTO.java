package com.icet.project.model.dto;

import com.icet.project.utill.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
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
