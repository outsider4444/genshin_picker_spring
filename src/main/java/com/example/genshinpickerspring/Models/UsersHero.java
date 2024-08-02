package com.example.genshinpickerspring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "users_heroes")
public class UsersHero {

    @Id
    @SequenceGenerator(
            name="user_hero_sequence",
            sequenceName = "user_hero_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_hero_sequence"
    )
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    private Hero hero;

    private Integer level;
    private Integer consta;
}
