package com.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entity.UserEntity;

public interface userRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login) ;

}
