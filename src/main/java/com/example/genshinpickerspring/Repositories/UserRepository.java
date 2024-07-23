package com.example.genshinpickerspring.Repositories;

import com.example.genshinpickerspring.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByHoyoId(Integer hoyoId);
}
