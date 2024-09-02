package com.example.genshinpickerspring.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "heroes")
public class Hero {
    @Id
    @SequenceGenerator(
            name="hero_sequence",
            sequenceName = "hero_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Hero_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String photoPath;

    @Column(nullable = false)
    private Integer starCount;

    private ElementType elementType;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersHero> usersHeroes;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TournamentHero> tournamentHeroes;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TournamentCreateHero> tournamentCreateHeroes;
}
