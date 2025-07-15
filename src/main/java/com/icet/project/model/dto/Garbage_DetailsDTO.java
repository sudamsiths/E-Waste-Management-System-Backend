package com.icet.project.model.dto;

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
    private String category;
    private Double price;
    private String location;
    private Double weight;
    private String description;
}
