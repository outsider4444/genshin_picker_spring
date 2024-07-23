package com.example.genshinpickerspring.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "Characters")
public class Character {
    @Id
    @SequenceGenerator(
            name="character_sequence",
            sequenceName = "character_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "character_sequence"
    )
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String photoPath;

    @OneToMany(mappedBy = "characterId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCharacterList> userCharacters;
}
