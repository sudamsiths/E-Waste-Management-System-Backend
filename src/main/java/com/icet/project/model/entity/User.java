package com.icet.project.model.entity;

import com.icet.project.utill.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String contactNo;
    @Column(unique = true)
    private String username;
    private String address;
    private String password;
    @Column(unique = true)
    private String email;
    @Transient
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Role role;

}
