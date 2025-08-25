package com.icet.project.service;

import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers(); // Fixed: removed unnecessary parameter
    void addUsers(UserDTO usersDTO);
    String login(String email, String password);
    List<UserDTO> searchUsers(String fullName);
    List<UserDTO> searchUsersByRole(String role);
    String verify(UserDTO users);
    List<UserDTO> findUsersByRole(String role);
    User findByUsername(String username);
    User updateUser(UserDTO userUpdateDTO);

    void deleteUser(String username);
}