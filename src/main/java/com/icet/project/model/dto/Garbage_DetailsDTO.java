package com.icet.project.model.dto;

import com.icet.project.utill.Category;
import com.icet.project.utill.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Garbage_DetailsDTO {
    private Long id;
    private String userName;
    private String title;
    private Category category;
    private String image;
    private Date SubmissionDate;
    private Integer points;
    private String location;
    private Double weight;
    private Status status;
    private String description;
    private String AgentName;
}
