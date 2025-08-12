package com.icet.project.service.impl;


import com.icet.project.model.dto.UserDTO;
import com.icet.project.model.entity.User;
import com.icet.project.repository.UserRepository;
import com.icet.project.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userDetailRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byUsername = userDetailRepository.findByUsername(username);
        System.out.println(byUsername);

        if ( byUsername == null) {
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException(("User not found with username: " + username));
        }

    return new UserPrincipal(modelMapper.map(byUsername, UserDTO.class));



    }
}
