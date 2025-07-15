package com.icet.project.service;

import com.icet.project.model.dto.UsersDTO;
import com.icet.project.model.entity.UsersEntity;
import com.icet.project.repository.UserRepository;
import com.icet.project.utill.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository ;

    ModelMapper modelMapper = new ModelMapper();

    public List<UsersDTO> getAllUsers(UsersDTO userDTOs) {
        List<UsersEntity> all = userRepository.findAll();
        List<UsersDTO> userDTO = new ArrayList<>();
        for (UsersEntity customerEntity : all ){
            UsersDTO map = modelMapper.map(customerEntity, UsersDTO.class);
            userDTO.add(map);
        }
        return userDTO;
    }

    public void addUsers(UsersDTO usersDTO) {
        userRepository.save(modelMapper.map(usersDTO , UsersEntity.class));
    }

    public String login(String email, String password) {
        UsersEntity user = userRepository.findByEmailAndPassword(email, password); // ✅ Corrected
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                return "Redirect to Admin Interface";
            } else {
                return "Redirect to Customer Interface";
            }
        } else {
            return "Invalid credentials";
        }
    }

}
