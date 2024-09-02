package com.example.genshinpickerspring.Repositories;

import com.example.genshinpickerspring.Models.TournamentCreateHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentCreateHeroRepository extends JpaRepository<TournamentCreateHero, Long> {
}
