package com.example.genshinpickerspring.Repositories;

import com.example.genshinpickerspring.Models.UsersHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersHeroRepository extends JpaRepository<UsersHero, Long> {
}
