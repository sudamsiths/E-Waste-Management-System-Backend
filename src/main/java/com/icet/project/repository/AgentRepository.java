package com.icet.project.repository;

import com.icet.project.model.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<AgentEntity , Long> {
}
