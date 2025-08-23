package com.icet.project.controller;

import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.LoginRequest;
import com.icet.project.model.entity.User;
import com.icet.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserController {

    final UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers(UserDTO usersDTO){
        return userService.getAllUsers(usersDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO users){
        return userService.verify(users);
    }

    // Add API prefix to match frontend call
    @PutMapping("/api/users/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserDTO userUpdateDTO) {
        try {
            System.out.println("Received update request for user: " + userUpdateDTO.getUsername());

            // Call service to update user
            User updatedUser = userService.updateUser(userUpdateDTO);

            // Hide password in response
            updatedUser.setPassword(null);

            System.out.println("User updated successfully: " + updatedUser.getUsername());
            return ResponseEntity.ok(updatedUser);

        } catch (Exception ex) {
            System.err.println("Error updating user: " + ex.getMessage());
            ex.printStackTrace();

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", ex.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    // Keep the original update endpoint as well for backward compatibility
    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfileOriginal(@RequestBody UserDTO userUpdateDTO) {
        return updateUserProfile(userUpdateDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUsers(@RequestBody UserDTO usersDTO){
        System.out.println("Received registration request: " + usersDTO);
        System.out.println("Contact number received: " + usersDTO.getContactNo());
        userService.addUsers(usersDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            System.out.println("=== GET USER REQUEST ===");
            System.out.println("Username: " + username);
            System.out.println("Request received successfully");

            User user = userService.findByUsername(username);
            if (user == null) {
                System.out.println("User not found: " + username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "User not found"));
            }

            // Hide password in response
            user.setPassword(null);

            System.out.println("User found and returned: " + user.getFullName());
            return ResponseEntity.ok(user);

        } catch (Exception ex) {
            System.err.println("Error fetching user: " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Internal server error: " + ex.getMessage()));
        }
    }

    @GetMapping("/search/users/by-name/{fullName}")
    public List<UserDTO> searchUsers(@PathVariable ("fullName") String fullName){
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

    // Add OPTIONS method handler for CORS preflight
    @RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
}