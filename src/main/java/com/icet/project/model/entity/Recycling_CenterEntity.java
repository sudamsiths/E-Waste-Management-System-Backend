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
@Table(name="recycling_center")
public class Recycling_CenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long centerId;
    private String location;
    private String centerName;
    private Integer contactNo;
    private String contactPerson;
    private String email;

}
