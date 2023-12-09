package com.example.appweddinghall.repository;

import com.example.appweddinghall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByPhone(String phone);
    Optional<User> findByPhone(String phone);
}
