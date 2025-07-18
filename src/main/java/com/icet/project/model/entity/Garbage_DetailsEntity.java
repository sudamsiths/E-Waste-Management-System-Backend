package com.icet.project.model.entity;

import com.icet.project.utill.category;
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
@Table(name="garbage_details")
public class Garbage_DetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private category category;
    private String image;
    private Double points;
    private String location;
    private Double weight;
    private String description;


//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UsersEntity user;
//
//    @ManyToOne
//    @JoinColumn(name = "agent_id")
//    private AgentEntity assignedAgent;
//
//    @ManyToOne
//    @JoinColumn(name = "recycling_center_id")
//    private Recycling_CenterEntity recyclingCenter;

}
