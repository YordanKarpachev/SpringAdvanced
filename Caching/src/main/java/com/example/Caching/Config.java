package com.example.Caching;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.StudentRepository;

@Configuration

public class Config {

    @Bean
    public StudentRepository studentRepository(){
        return new StudentRepository();
    }


}
