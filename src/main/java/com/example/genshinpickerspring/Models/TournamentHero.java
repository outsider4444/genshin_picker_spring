package com.example.genshinpickerspring.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tournament_heroes")
public class TournamentHero {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;

    private Integer turnNumber;

    private boolean isBanned;
    private boolean isImmune;
    private boolean isPicked;

}
