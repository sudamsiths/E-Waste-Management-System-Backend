package com.icet.project.model.entity;

import com.icet.project.utill.Category;
import com.icet.project.utill.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

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
    private String userName;
    private String title;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String image;
    private Integer points;
    private Date SubmissionDate;
    private String location;
    private Double weight;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private Long AgentID;

}
