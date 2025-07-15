package com.icet.project.repository;

import com.icet.project.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity , Long> {
}
