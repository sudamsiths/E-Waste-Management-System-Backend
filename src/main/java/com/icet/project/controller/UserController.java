package com.icet.project.controller;

import com.icet.project.model.dto.UsersDTO;
import com.icet.project.model.entity.LoginRequest;
import com.icet.project.service.UserService;
import com.icet.project.utill.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/getAll")
    public List<UsersDTO>getAllUsers(UsersDTO usersDTO){
        return userService.getAllUsers(usersDTO);
    }

    @PostMapping("/add")
    public void addUsers(@RequestBody UsersDTO usersDTO ){
        userService.addUsers(usersDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(result);
    }
    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable ("id") Long id, @RequestBody UsersDTO usersDTO) {
        System.out.println("Updating user with ID: " + id + " with data: " + usersDTO);
        userService.updateUser(id,usersDTO);
    }

    @GetMapping("/search/users/{fullName}")
    public List<UsersDTO>searchUsers(@PathVariable ("fullName") String fullName){
        System.out.println("Searching for users with full name: " + fullName);
        return userService.searchUsers(fullName);
    }

    @GetMapping("/search/users/by-role/{role}")
    public List<UsersDTO> searchUsersByRole(@PathVariable("role") String role) {
        return userService.searchUsersByRole(role);
    }

}
