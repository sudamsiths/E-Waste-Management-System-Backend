package com.icet.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Recycling_CenterDTO {
    private Long centerId;
    private String location;
    private String centerName;
    private String contactNo;
    private String contactPerson;
    private String email;
}
