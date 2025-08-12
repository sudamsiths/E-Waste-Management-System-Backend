package com.icet.project.repository;

import com.icet.project.model.entity.User;
import com.icet.project.utill.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFullName(String fullName);
    List<User> findByRole(Role role);
    User findByUsername(String username);
    User findByEmail(String email);
}