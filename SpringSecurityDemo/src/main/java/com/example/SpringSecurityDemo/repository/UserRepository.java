package com.example.SpringSecurityDemo.repository;


import com.example.SpringSecurityDemo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findUserEntityByEmail(String email);
}
