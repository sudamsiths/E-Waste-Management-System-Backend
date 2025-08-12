package com.icet.project.service.impl;

import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.User;
import com.icet.project.repository.UserRepository;
import com.icet.project.service.JWTService;
import com.icet.project.service.UserService;
import com.icet.project.utill.Role; // Assuming this enum defines ADMIN and CUSTOMER roles
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JWTService jwtService;

    final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    final ModelMapper modelMapper;

    final BCryptPasswordEncoder passwordEncoder;


    public List<UserDTO> getAllUsers(UserDTO userDTOs) {
        List<User> all = userRepository.findAll();//get All Users
        List<UserDTO> userDTO = new ArrayList<>();
        for (User customerEntity : all) {
            UserDTO map = modelMapper.map(customerEntity, UserDTO.class);
            userDTO.add(map);
        }
        return userDTO;
    }

    public void addUsers(UserDTO usersDTO) {
        if (Objects.equals(usersDTO.getPassword(), usersDTO.getConfirmPassword())) {
            if (userRepository.findByEmail(usersDTO.getEmail()) != null) {
                throw new IllegalArgumentException("Email already exists");
            }else {
                usersDTO.setPassword(passwordEncoder.encode(usersDTO.getPassword()));//encrypt users password
                userRepository.save(modelMapper.map(usersDTO, User.class));//save user
            }
        } else {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);//custom method to find user by email

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                if (user.getRole() == Role.ADMIN) {
                    return "Redirect to Admin Interface";
                } else {
                    return "Redirect to Customer Interface";
                }
            } else {
                return "Invalid Password"; // Passwords don't match
            }
        } else {
            return "Email OR Password Was Cannot be Null"; // User not found
        }
    }

    @Override
    public void updateUser(Long id, UserDTO usersDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (Objects.equals(usersDTO.getPassword(), usersDTO.getConfirmPassword())) {
                existingUser.setFullName(usersDTO.getFullName());
                existingUser.setEmail(usersDTO.getEmail());
                existingUser.setUsername(usersDTO.getUsername());
                existingUser.setRole(usersDTO.getRole());
                existingUser.setPassword(passwordEncoder.encode(usersDTO.getPassword())); // Encrypt password
                userRepository.save(existingUser);
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    @Override
    public List<UserDTO> searchUsers(String fullName) {
        List<User> users = userRepository.findByFullName(fullName);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO dto = modelMapper.map(user, UserDTO.class);
            userDTOs.add(dto);
        }
        return userDTOs;
    }
    @Override
    public List<UserDTO> searchUsersByRole(String role) {
        Role roleEnum = Role.valueOf(role.toUpperCase());
        List<User> users = userRepository.findByRole(roleEnum);// custom method Find users by role
        return users.stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    public String verify(UserDTO user) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authenticate.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "User is not authenticated";

    }

}
