package com.icet.project.repository;

import com.icet.project.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> { // Assuming Long is your ID type
    UsersEntity findByEmail(String email);
}