package com.icet.project.service.impl;

import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.User;
import com.icet.project.repository.UserRepository;
import com.icet.project.service.JWTService;
import com.icet.project.service.UserService;
import com.icet.project.utill.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> all = userRepository.findAll();
        List<UserDTO> userDTO = new ArrayList<>();
        for (User userEntity : all) {
            UserDTO map = modelMapper.map(userEntity, UserDTO.class);
            map.setPassword(null);
            userDTO.add(map);
        }
        return userDTO;
    }

    @Override
    public void addUsers(UserDTO usersDTO) {
        try {
            if (usersDTO.getUsername() == null || usersDTO.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("Username is required");
            }
            if (usersDTO.getPassword() == null || usersDTO.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("Password is required");
            }
            if (usersDTO.getEmail() == null || usersDTO.getEmail().trim().isEmpty()) {
                throw new IllegalArgumentException("Email is required");
            }

            if (userRepository.findByUsername(usersDTO.getUsername()) != null) {
                throw new IllegalArgumentException("Username already exists");
            }

            if (userRepository.findByEmail(usersDTO.getEmail()) != null) {
                throw new IllegalArgumentException("Email already exists");
            }

            User user = modelMapper.map(usersDTO, User.class);
            user.setPassword(passwordEncoder.encode(usersDTO.getPassword()));

            if (user.getRole() == null) {
                user.setRole(Role.CUSTOMER);
            }

            userRepository.save(user);
            System.out.println("User registered successfully: " + user.getUsername());
        } catch (Exception ex) {
            System.err.println("Error in addUsers: " + ex.getMessage());
            throw new RuntimeException("Failed to register user: " + ex.getMessage());
        }
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                if (user.getRole() == Role.ADMIN) {
                    return "Redirect to Admin Interface";
                } else {
                    return "Redirect to Customer Interface";
                }
            } else {
                return "Invalid Password";
            }
        } else {
            return "Email OR Password Was Cannot be Null";
        }
    }

    @Override
    public User updateUser(UserDTO userUpdateDTO) {
        try {
            System.out.println("Updating user: " + userUpdateDTO.getUsername());

            String username = userUpdateDTO.getUsername();
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty");
            }

            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found with username: " + username);
            }

            if (userUpdateDTO.getFullName() != null && !userUpdateDTO.getFullName().trim().isEmpty()) {
                user.setFullName(userUpdateDTO.getFullName().trim());
            }

            if (userUpdateDTO.getContactNo() != null && !userUpdateDTO.getContactNo().trim().isEmpty()) {
                user.setContactNo(userUpdateDTO.getContactNo().trim());
            }

            if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().trim().isEmpty()) {
                if (!user.getEmail().equals(userUpdateDTO.getEmail().trim())) {
                    User existingUser = userRepository.findByEmail(userUpdateDTO.getEmail().trim());
                    if (existingUser != null && !existingUser.getUserId().equals(user.getUserId())) {
                        throw new IllegalArgumentException("Email already exists");
                    }
                }
                user.setEmail(userUpdateDTO.getEmail().trim());
            }

            if (userUpdateDTO.getAddress() != null && !userUpdateDTO.getAddress().trim().isEmpty()) {
                user.setAddress(userUpdateDTO.getAddress().trim());
            }

            if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().trim().isEmpty()) {
                System.out.println("Updating password for user: " + username);
                user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            }

            // Save and return updated user
            User savedUser = userRepository.save(user);
            System.out.println("User updated successfully: " + savedUser.getFullName());

            return savedUser;

        } catch (Exception ex) {
            System.err.println("Error in updateUser service: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Failed to update user: " + ex.getMessage());
        }
    }

    @Override
    public void deleteUser(String username) {
        try {
            System.out.println("Deleting user: " + username);

            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty");
            }

            User user = userRepository.findByUsername(username.trim());
            if (user == null) {
                throw new RuntimeException("User not found with username: " + username);
            }

            userRepository.delete(user);
            System.out.println("User deleted successfully: " + username);

        } catch (Exception ex) {
            System.err.println("Error deleting user: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Failed to delete user: " + ex.getMessage());
        }
    }

    @Override
    public void updateUserNameAndPassword(Long id, String newUsername, String newPassword) {
        try {
            log.info("Updating credentials for user id: {}", id);
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

            // Update username if provided and different
            if (newUsername != null && !newUsername.trim().isEmpty() &&
                    !newUsername.trim().equals(user.getUsername())) {
                User existing = userRepository.findByUsername(newUsername.trim());
                if (existing != null && !existing.getUserId().equals(id)) {
                    throw new IllegalArgumentException("Username already exists");
                }
                user.setUsername(newUsername.trim());
            }

            // Update password if provided
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword.trim()));
            }

            userRepository.save(user);
            log.info("Credentials updated for user id: {}", id);
        } catch (Exception e) {
            log.error("Failed to update credentials: {}", e.getMessage());
            throw new RuntimeException("Failed to update credentials: " + e.getMessage());
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            System.out.println("Finding user by username: " + username);

            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty");
            }

            User user = userRepository.findByUsername(username.trim());

            if (user != null) {
                System.out.println("User found: " + user.getFullName());
            } else {
                System.out.println("User not found with username: " + username);
            }

            return user;

        } catch (Exception ex) {
            System.err.println("Error finding user by username: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Failed to find user: " + ex.getMessage());
        }
    }

    @Override
    public List<UserDTO> searchUsers(String fullName) {
        List<User> users = userRepository.findByFullName(fullName);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO dto = modelMapper.map(user, UserDTO.class);
            dto.setPassword(null); // Don't include password
            userDTOs.add(dto);
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> searchUsersByRole(String role) {
        Role roleEnum = Role.valueOf(role.toUpperCase());
        List<User> users = userRepository.findByRole(roleEnum);
        return users.stream()
                .map(entity -> {
                    UserDTO dto = modelMapper.map(entity, UserDTO.class);
                    dto.setPassword(null); // Don't include password
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String verify(UserDTO user) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authenticate.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }

            return "User is not authenticated";
        } catch (Exception ex) {
            System.err.println("Authentication failed: " + ex.getMessage());
            return "User is not authenticated";
        }
    }

    @Override
    public List<UserDTO> findUsersByRole(String role) {
        Role roleEnum = Role.valueOf(role.toUpperCase());
        List<User> users = userRepository.findByRole(roleEnum);
        return users.stream()
                .map(entity -> {
                    UserDTO dto = modelMapper.map(entity, UserDTO.class);
                    dto.setPassword(null); // Don't include password
                    return dto;
                })
                .collect(Collectors.toList());
    }
}