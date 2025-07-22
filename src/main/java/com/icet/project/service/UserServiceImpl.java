package com.icet.project.service;

import com.icet.project.model.dto.UsersDTO;
import com.icet.project.model.entity.UsersEntity;
import com.icet.project.repository.UserRepository;
import com.icet.project.utill.Role; // Assuming this enum defines ADMIN and CUSTOMER roles
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsersDTO> getAllUsers(UsersDTO userDTOs) {
        List<UsersEntity> all = userRepository.findAll();
        List<UsersDTO> userDTO = new ArrayList<>();
        for (UsersEntity customerEntity : all) {
            UsersDTO map = modelMapper.map(customerEntity, UsersDTO.class);
            userDTO.add(map);
        }
        return userDTO;
    }

    public void addUsers(UsersDTO usersDTO) {
        usersDTO.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        userRepository.save(modelMapper.map(usersDTO, UsersEntity.class));
    }

    public String login(String email, String password) {
        UsersEntity user = userRepository.findByEmail(email);

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
}