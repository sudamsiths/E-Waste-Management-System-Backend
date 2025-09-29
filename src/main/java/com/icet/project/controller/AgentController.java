package com.icet.project.controller;

import com.icet.project.model.dto.AgentDTO;
import com.icet.project.model.entity.AgentEntity;
import com.icet.project.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
@CrossOrigin
@RequiredArgsConstructor

public class AgentController {

    final AgentService agentService;

    @GetMapping("/getAll")
    public List<AgentDTO> getAllAgents(AgentDTO agentDTO) {
        System.out.println("Fetching all agents with filter: " + agentDTO);
        return agentService.getAllAgents(agentDTO);
    }
    @GetMapping("/GetAll/AgentsName")
    public List<String> getAllAgentsNames() {
        return agentService.getAllAgentsNames();
    }

    @GetMapping("/search/{fullName}")
    public List<AgentDTO> getAgentById(@PathVariable String fullName) {
        return agentService.getAgentById(fullName);
    }

    @PostMapping("/add")
    public AgentDTO createAgent(@RequestBody AgentEntity agent) {
        return agentService.createAgent(agent);
    }

    @PostMapping("/login")
    public List<AgentDTO>loginAgent(@RequestBody AgentDTO agentDTO){
        return  agentService.loginAgent(agentDTO.getEmail(), agentDTO.getPassword());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
    }

    @PutMapping("/update/{id}")
    public AgentDTO updateAgent(@PathVariable Long id, @RequestBody AgentEntity agent) {
        agent.setAgentId(id);
        return agentService.updateAgent(agent);
    }

    @GetMapping("/search/ByBranch/{assignBranch}")
    public List<AgentDTO> getAgentsByBranch(@PathVariable String assignBranch) {
        return agentService.getAgentsByBranch(assignBranch);
    }

    @GetMapping("/search/ByStatus/{status}")
    public List<AgentDTO> getAgentsByStatus(@PathVariable ("status") String status) {
        return agentService.getAgentsByStatus(status);
    }
}
