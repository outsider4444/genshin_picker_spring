package com.example.genshinpickerspring.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tournaments")
public class Tournament {
    @Id
    private Long id;
    private Long username1;
    private Long username2;
    private Boolean isOpen;
    private String code;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TournamentHero> tournamentHeroes;


    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TournamentCreateHero> tournamentCreateHeroes;
}
