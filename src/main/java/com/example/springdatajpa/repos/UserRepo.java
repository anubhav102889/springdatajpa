package com.example.springdatajpa.repos;

import com.example.springdatajpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
}
