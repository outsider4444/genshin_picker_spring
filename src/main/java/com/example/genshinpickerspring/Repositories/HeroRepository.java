package com.example.genshinpickerspring.Repositories;

import com.example.genshinpickerspring.Models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
}
