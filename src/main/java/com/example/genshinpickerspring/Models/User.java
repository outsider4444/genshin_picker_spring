package com.example.genshinpickerspring.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name="user_character_sequence",
            sequenceName = "user_character_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Integer hoyoId; // ID аккаунта hoyoverse

    private Date dateCreated;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsersHero> userHeroes;

    @JsonManagedReference("owner-tournaments")
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Tournament> ownedTournaments;

    @JsonManagedReference("opponent-tournaments")
    @OneToMany(mappedBy = "opponent", fetch = FetchType.EAGER)
    private List<Tournament> opponentTournaments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public String toString(){
        return this.username;
    }

}
