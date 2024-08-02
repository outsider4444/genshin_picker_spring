package com.example.genshinpickerspring.Repositories;

import com.example.genshinpickerspring.Models.Tournament;
import com.example.genshinpickerspring.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
