package com.icet.project.repository;

import com.icet.project.model.entity.UsersEntity;
import com.icet.project.utill.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findByEmail(String email);
    List<UsersEntity> findByFullName(String fullName);
    List<UsersEntity> findByRole(Role role);
}