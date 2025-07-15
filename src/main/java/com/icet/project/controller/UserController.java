package com.icet.project.controller;

import com.icet.project.model.dto.UsersDTO;
import com.icet.project.model.entity.LoginRequest;
import com.icet.project.model.entity.UsersEntity;
import com.icet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


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

}
