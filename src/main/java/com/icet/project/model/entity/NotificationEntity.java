package com.icet.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    private String message;
    private Date dateSent;


//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = true)
//    private UsersEntity recipient;
//
//    @ManyToOne
//    @JoinColumn(name = "agent_id", nullable = true)
//    private AgentEntity recipientAgent;
}
