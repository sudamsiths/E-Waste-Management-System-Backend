package com.icet.project.repository;

import com.icet.project.model.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity , Long> {
    Optional<AgentEntity> findByFullName(String fullName);
}
