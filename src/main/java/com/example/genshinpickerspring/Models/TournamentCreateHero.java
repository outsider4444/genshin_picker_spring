package com.example.genshinpickerspring.Models;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.Tournament;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;

    private Boolean isDisabled;

    private boolean isBanned;
    private boolean isImmune;
}
