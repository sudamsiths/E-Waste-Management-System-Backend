package com.icet.project.repository;

import com.icet.project.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByEmailAndPassword(String email, String password);
}
