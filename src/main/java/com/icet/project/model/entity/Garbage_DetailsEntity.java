package com.icet.project.model.entity;

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
    private String category;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    private Double price;
    private String location;
    private Double weight;
    private String description;


}
