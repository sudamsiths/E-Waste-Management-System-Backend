package com.icet.project.service;

import com.icet.project.model.dto.AgentDTO;
import com.icet.project.model.entity.AgentEntity;

import java.util.List;

public interface AgentService {
    List<AgentDTO> getAllAgents(AgentDTO agentDTO );
    AgentDTO createAgent(AgentEntity agent);
    List<AgentDTO> getAgentById(String fullName);
    void deleteAgent(Long id);
}
