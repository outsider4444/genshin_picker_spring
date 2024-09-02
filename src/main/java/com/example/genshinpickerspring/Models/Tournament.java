package com.example.genshinpickerspring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tournaments")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("owner-tournaments")
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @JsonBackReference("opponent-tournaments")
    @ManyToOne
    @JoinColumn(name = "opponent_id")
    private User opponent;

    private Boolean isOpen;
    private String code;
    private Boolean isArchive;

    @JsonManagedReference("tournament-heroes")
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TournamentHero> tournamentHeroes;

    @JsonManagedReference("tournament-create-heroes")
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TournamentCreateHero> tournamentCreateHeroes;
}
