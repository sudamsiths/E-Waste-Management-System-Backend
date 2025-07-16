package com.icet.project.model.entity;

import com.icet.project.utill.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Long contactNo;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Garbage_DetailsEntity> garbageItems;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FeedbackEntity> feedbacks;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<NotificationEntity> notifications;
}
