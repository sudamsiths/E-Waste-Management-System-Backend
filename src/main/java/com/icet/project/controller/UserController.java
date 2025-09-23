package com.icet.project.controller;

import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.User;
import com.icet.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO users){
        try {
            String token = userService.verify(users);
            if ("User is not authenticated".equals(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Invalid credentials"));
            }

            // Return token and user info
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", users.getUsername());
            log.info("User logged in successfully: " + users.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Login failed: " + ex.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUsers(@RequestBody UserDTO usersDTO){
        try {
            System.out.println("Received registration request: " + usersDTO);
            log.info("Attempting to register user: " + usersDTO.getUsername());
            userService.addUsers(usersDTO);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Registration failed: " + ex.getMessage()));
        }
    }

    // Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                System.out.println("User not found: " + username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "User not found"));
            }

            // Convert to DTO and hide password
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setFullName(user.getFullName());
            userDTO.setContactNo(user.getContactNo());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setRole(user.getRole());
            // Don't set password

            System.out.println("User found and returned: " + user.getFullName());
            return ResponseEntity.ok(userDTO);

        } catch (Exception ex) {
            System.err.println("Error fetching user: " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Internal server error: " + ex.getMessage()));
        }
    }

    // Fixed update
    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserDTO userUpdateDTO) {
        try {
            System.out.println("Received update request for user: " + userUpdateDTO.getUsername());

            // Validate required fields
            if (userUpdateDTO.getUsername() == null || userUpdateDTO.getUsername().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Username is required"));
            }

            // Call service to update user
            User updatedUser = userService.updateUser(userUpdateDTO);

            // Convert to DTO for response
            UserDTO responseDTO = new UserDTO();
            responseDTO.setUserId(updatedUser.getUserId());
            responseDTO.setFullName(updatedUser.getFullName());
            responseDTO.setContactNo(updatedUser.getContactNo());
            responseDTO.setUsername(updatedUser.getUsername());
            responseDTO.setEmail(updatedUser.getEmail());
            responseDTO.setAddress(updatedUser.getAddress());
            responseDTO.setRole(updatedUser.getRole());

            System.out.println("User updated successfully: " + updatedUser.getUsername());
            return ResponseEntity.ok(responseDTO);

        } catch (Exception ex) {
            System.err.println("Error updating user: " + ex.getMessage());
            ex.printStackTrace();

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", ex.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
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

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Deletion failed: " + ex.getMessage()));
        }
    }

    @PutMapping("/update/credentials/{id}")
    public ResponseEntity<?> updateCredentials(@PathVariable Long id, @RequestBody UserDTO body) {
        userService.updateUserNameAndPassword(id, body.getUsername(), body.getPassword());
        return ResponseEntity.ok(Map.of("message", "Credentials updated"));
    }
}