package com.icet.project.model.dto;

import com.icet.project.utill.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Garbage_DetailsDTO {
    private Long id;
    private String title;
    private Category category;
    private String image;
    private Double points;
    private String location;
    private Double weight;
    private String description;
}
