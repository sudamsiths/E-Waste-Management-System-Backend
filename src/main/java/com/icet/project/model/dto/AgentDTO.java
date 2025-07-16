package com.icet.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgentDTO {
    private Long agentId;
    private String fullName;
    private String email;
    private String assignBranch;
    private String status;
    private String contactNo;
}
