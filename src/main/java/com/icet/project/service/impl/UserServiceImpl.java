package com.icet.project.service.impl;

import com.icet.project.model.dto.UsersDTO;
import com.icet.project.model.entity.UsersEntity;
import com.icet.project.repository.UserRepository;
import com.icet.project.service.UserService;
import com.icet.project.utill.Role; // Assuming this enum defines ADMIN and CUSTOMER roles
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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


    final UserRepository userRepository;

    final ModelMapper modelMapper;

    final BCryptPasswordEncoder passwordEncoder;


    public List<UsersDTO> getAllUsers(UsersDTO userDTOs) {
        List<UsersEntity> all = userRepository.findAll();//get All Users
        List<UsersDTO> userDTO = new ArrayList<>();
        for (UsersEntity customerEntity : all) {
            UsersDTO map = modelMapper.map(customerEntity, UsersDTO.class);
            userDTO.add(map);
        }
        return userDTO;
    }

    public void addUsers(UsersDTO usersDTO) {
        if (Objects.equals(usersDTO.getPassword(), usersDTO.getConfirmPassword())) {
            if (userRepository.findByEmail(usersDTO.getEmail()) != null) {
                throw new IllegalArgumentException("Email already exists");
            }else {
                usersDTO.setPassword(passwordEncoder.encode(usersDTO.getPassword()));//encrypt users password
                userRepository.save(modelMapper.map(usersDTO, UsersEntity.class));//save user
            }
        } else {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    public String login(String email, String password) {
        UsersEntity user = userRepository.findByEmail(email);//custom method to find user by email

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                if (user.getRole() == Role.ADMIN) {
                    return "Redirect to Admin Interface";
                } else {
                    return "Redirect to Customer Interface";
                }
            } else {
                return "Invalid credentials"; // Passwords don't match
            }
        } else {
            return "Invalid credentials"; // User not found
        }
    }

    @Override
    public void updateUser(Long id, UsersDTO usersDTO) {
        Optional<UsersEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UsersEntity existingUser = optionalUser.get();
            existingUser.setFullName(usersDTO.getFullName());
            existingUser.setContactNo(Long.valueOf(usersDTO.getContactNo()));
            existingUser.setUsername(usersDTO.getUsername());
            existingUser.setAddress(usersDTO.getAddress());
            existingUser.setEmail(usersDTO.getEmail());
            existingUser.setRole(usersDTO.getRole());

            if (usersDTO.getPassword() != null && !usersDTO.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
            }
            userRepository.save(existingUser);
        }
    }

    @Override
    public List<UsersDTO> searchUsers(String fullName) {
        List<UsersEntity> users = userRepository.findByFullName(fullName);
        List<UsersDTO> userDTOs = new ArrayList<>();
        for (UsersEntity user : users) {
            UsersDTO dto = modelMapper.map(user, UsersDTO.class);
            userDTOs.add(dto);
        }
        return userDTOs;
    }
    @Override
    public List<UsersDTO> searchUsersByRole(String role) {
        Role roleEnum = Role.valueOf(role.toUpperCase());
        List<UsersEntity> users = userRepository.findByRole(roleEnum);// custom method Find users by role
        return users.stream()
                .map(entity -> modelMapper.map(entity, UsersDTO.class))
                .collect(Collectors.toList());
    }
}
