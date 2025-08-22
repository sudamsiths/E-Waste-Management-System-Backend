package com.icet.project.service;

import com.icet.project.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers(UserDTO usersDTO);
    void addUsers(UserDTO usersDTO);
    String login(String email, String password);
    void updateUser(Long id , UserDTO usersDTO);
    List<UserDTO> searchUsers(String fullName);
    List<UserDTO> searchUsersByRole(String role);
    String verify(UserDTO users);
    List<UserDTO> findUsersByRole(String role);
}
