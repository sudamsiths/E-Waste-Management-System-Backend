package com.icet.project.model.entity;

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
@Table(name="agent")
public class AgentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long agentId;
    private String fullName;
    private String email;
    private String assignBranch;
    private String status;
    private Integer contactNo;
}
