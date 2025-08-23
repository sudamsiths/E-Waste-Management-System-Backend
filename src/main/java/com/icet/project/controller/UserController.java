package com.icet.project.controller;

import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.LoginRequest;
import com.icet.project.model.entity.User;
import com.icet.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    final UserService userService;

    @GetMapping("/getAll")
    public List<UserDTO>getAllUsers(UserDTO usersDTO){
        return userService.getAllUsers(usersDTO);
    }


    @PostMapping("/login")
    public String login(@RequestBody UserDTO users){
        return userService.verify(users);

    }

    @PostMapping("/register")
    public ResponseEntity<?> addUsers(@RequestBody UserDTO usersDTO){
        System.out.println("Received registration request: " + usersDTO);
        System.out.println("Contact number received: " + usersDTO.getContactNo());
        userService.addUsers(usersDTO);
        return ResponseEntity.ok("User registered successfully");
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(username, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/search/users/by-name/{fullName}")
    public List<UserDTO>searchUsers(@PathVariable ("fullName") String fullName){
        System.out.println("Searching for users with full name: " + fullName);
        return userService.searchUsers(fullName);
    }

    @GetMapping("/search/users/by-role/{role}")
    public List<UserDTO> searchUsersByRole(@PathVariable("role") String role) {
        return userService.searchUsersByRole(role);
    }

    @GetMapping("/find/{role}")
    public List<UserDTO> findUsersByRole(@PathVariable("role") String role) {
        System.out.println("Finding users with role: " + role);
        return userService.findUsersByRole(role);
    }

}
