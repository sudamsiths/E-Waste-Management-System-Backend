package com.icet.project.model.entity;

import com.icet.project.utill.Status;
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
@Table(name="agent")
public class AgentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long agentId;
    private String fullName;
    private String email;
    private String assignBranch;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer contactNo;

//    @OneToMany(mappedBy = "assignedAgent", cascade = CascadeType.ALL)
//    private List<Garbage_DetailsEntity> assignedGarbage;
//
//    @OneToMany(mappedBy = "recipientAgent", cascade = CascadeType.ALL)
//    private List<NotificationEntity> notifications;


    
}
