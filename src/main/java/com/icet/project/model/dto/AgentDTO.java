package com.icet.project.model.dto;

import com.icet.project.utill.Status;
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
    private String password;
    private String assignBranch;
    private Status status;
    private String contactNo;
    private String address;
}
