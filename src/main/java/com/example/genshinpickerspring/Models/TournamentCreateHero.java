package com.example.genshinpickerspring.Models;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.Tournament;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tournament_create_heroes")
public class TournamentCreateHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("tournament-create-heroes")
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;

    private boolean isBanned; // Забанен ли герой
    private boolean isImmune; // Будет ли у героя иммун при пиках/банах
}
