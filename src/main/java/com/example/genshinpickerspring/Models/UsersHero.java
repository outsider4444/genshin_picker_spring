package com.example.genshinpickerspring.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "UsersHeroes")
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

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "heroId", nullable = false)
    private Long heroId;

    private Integer level;
    private Integer consta;
}
