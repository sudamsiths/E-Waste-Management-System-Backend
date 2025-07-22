package com.icet.project.service.impl;

import com.icet.project.model.dto.AgentDTO;
import com.icet.project.model.entity.AgentEntity;
import com.icet.project.repository.AgentRepository;
import com.icet.project.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AgentServiceImpl implements AgentService {

     final AgentRepository agentRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<AgentDTO> getAllAgents(AgentDTO agentDTO ) {
        List<AgentEntity> all = agentRepository.findAll();
        List<AgentDTO> agentDTOS = new ArrayList<>();
        for (AgentEntity agentEntity : all ){
            AgentDTO map = modelMapper.map(agentEntity, AgentDTO.class);
            agentDTOS.add(map);
        }
        return agentDTOS;
    }


    public AgentDTO createAgent(AgentEntity agent) {
        AgentEntity agentEntity = modelMapper.map(agent, AgentEntity.class);
        agentEntity = agentRepository.save(agentEntity);
        return modelMapper.map(agentEntity, AgentDTO.class);

    }

    public void deleteAgent(Long id) {
        Optional<AgentEntity> agentEntity = agentRepository.findById(id);
        if (agentEntity.isPresent()) {
            agentRepository.delete(agentEntity.get());
        } else {
            throw new RuntimeException("Agent not found with id: " + id);
        }
    }

    public List<AgentDTO> getAgentById(String fullName) {
        Optional<AgentEntity> byName = agentRepository.findByFullName(fullName);
        List<AgentDTO> agentDTOS = new ArrayList<>();
        if (byName.isPresent()) {
            AgentEntity agentEntity = byName.get();
            AgentDTO map = modelMapper.map(agentEntity, AgentDTO.class);
            agentDTOS.add(map);
        }
        return agentDTOS;
    }
}
