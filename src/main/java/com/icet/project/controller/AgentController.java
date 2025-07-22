package com.icet.project.controller;

import com.icet.project.model.dto.AgentDTO;
import com.icet.project.model.entity.AgentEntity;
import com.icet.project.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor

public class AgentController {

    final AgentService agentService;

    @GetMapping("/getAll")
    public List<AgentDTO> getAllAgents(AgentDTO agentDTO ) {
        return agentService.getAllAgents(agentDTO);
    }

    @GetMapping("/search/{fullName}")
    public List<AgentDTO> getAgentById(@PathVariable String fullName) {
        return agentService.getAgentById(fullName);
    }

    @PostMapping("/add")
    public AgentDTO createAgent(@RequestBody AgentEntity agent) {
        return agentService.createAgent(agent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAgent(@PathVariable Long id) {
         agentService.deleteAgent(id);
    }
}
