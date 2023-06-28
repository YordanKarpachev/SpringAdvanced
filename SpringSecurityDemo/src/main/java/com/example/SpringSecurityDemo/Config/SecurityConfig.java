package com.example.SpringSecurityDemo.Config;


import com.example.SpringSecurityDemo.model.enums.UserRoleEnum;
import com.example.SpringSecurityDemo.repository.UserRepository;
import com.example.SpringSecurityDemo.services.UserDetailServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig {


    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                // defines which pages will be authorized
                        authorizeHttpRequests().

        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll().
                requestMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name()).
                requestMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name()).
                anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/", true).
                failureForwardUrl("/users/login-error");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailServiceImpl(userRepository);
    }


}
