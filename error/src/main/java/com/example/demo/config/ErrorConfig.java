package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class ErrorConfig {

@Bean
    public HandlerExceptionResolver simpleMappingExceptionResolver(){

    SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();

    Properties properties = new Properties();

    properties.setProperty(NullPointerException.class.getSimpleName(), "null-pointer-exception");

    smer.setExceptionMappings(properties);

    return smer;
}



}
