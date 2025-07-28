package com.icet.project.repository;

import com.icet.project.model.entity.AgentEntity;
import com.icet.project.utill.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity , Long> {

    Optional<AgentEntity> findByFullName(String fullName);
    List<AgentEntity> findByassignBranch(String assignBranch);

    List<AgentEntity> findByStatus(Status status);
}
