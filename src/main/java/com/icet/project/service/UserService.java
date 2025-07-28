package com.icet.project.service;

import com.icet.project.model.dto.UsersDTO;
import com.icet.project.utill.Role;

import java.util.List;

public interface UserService {

    List<UsersDTO> getAllUsers(UsersDTO usersDTO);
    void addUsers(UsersDTO usersDTO);
    String login(String email, String password);
    void updateUser(Long id ,UsersDTO usersDTO);
    List<UsersDTO> searchUsers(String fullName);
    List<UsersDTO> searchUsersByRole(String role);

}
