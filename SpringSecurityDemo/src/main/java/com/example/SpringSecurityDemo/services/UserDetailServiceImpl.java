package com.example.SpringSecurityDemo.services;

import com.example.SpringSecurityDemo.model.entity.UserEntity;
import com.example.SpringSecurityDemo.model.entity.UserRoleEntity;
import com.example.SpringSecurityDemo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailsService {

   private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      return   this.userRepository.findUserEntityByEmail(username).map(this::map)
                .orElseThrow(() ->new UsernameNotFoundException("username not found"));


        
    }

    private UserDetails  map(UserEntity userEntity) {
        return new User(userEntity.getEmail(), userEntity.getPassword(), createAuthorities(userEntity));
    }

  private List<GrantedAuthority> createAuthorities(UserEntity userEntity){
        return userEntity.getRoles().stream().map(this::mapRole).toList();
  }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity){
        return new SimpleGrantedAuthority(userRoleEntity.getRole().name());
    }

}
