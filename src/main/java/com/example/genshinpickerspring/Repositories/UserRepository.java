package com.example.genshinpickerspring.Repositories;

import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByHoyoId(Integer hoyoId);
}
