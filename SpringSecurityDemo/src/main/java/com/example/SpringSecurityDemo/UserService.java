package com.example.SpringSecurityDemo;


import com.example.SpringSecurityDemo.model.dtos.UserRegistrationDTO;
import com.example.SpringSecurityDemo.model.entity.UserEntity;
import com.example.SpringSecurityDemo.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;


  public UserService(UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      UserDetailsService userDetailsService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  public void registerUser(UserRegistrationDTO registrationDTO,
                           Consumer<Authentication> successfulLoginProcessor) {

    UserEntity userEntity = new UserEntity().
        setFirstName(registrationDTO.getFirstName()).
        setLastName(registrationDTO.getLastName()).
        setEmail(registrationDTO.getEmail()).
        setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

    userRepository.save(userEntity);

    UserDetails userDetails = userDetailsService.loadUserByUsername(registrationDTO.getEmail());

    Authentication authentication = new UsernamePasswordAuthenticationToken(
        userDetails,
        userDetails.getPassword(),
        userDetails.getAuthorities()
    );

    successfulLoginProcessor.accept(authentication);
  }

}
