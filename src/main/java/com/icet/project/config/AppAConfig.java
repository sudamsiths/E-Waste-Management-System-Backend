package com.icet.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppAConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
