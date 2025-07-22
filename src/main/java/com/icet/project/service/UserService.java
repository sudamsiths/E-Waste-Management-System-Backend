package com.icet.project.service;

import com.icet.project.model.dto.UsersDTO;

import java.util.List;

public interface UserService {
    List<UsersDTO> getAllUsers(UsersDTO usersDTO);

    void addUsers(UsersDTO usersDTO);

    String login(String email, String password);
}
