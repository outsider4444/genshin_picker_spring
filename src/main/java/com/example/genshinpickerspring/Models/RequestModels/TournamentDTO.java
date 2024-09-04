package com.example.genshinpickerspring.Models.RequestModels;

import com.example.genshinpickerspring.Models.TournamentCreateHero;
import com.example.genshinpickerspring.Models.TournamentHero;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public class TournamentDTO {

    private Long id;
    private UserTournament owner;
    private UserTournament opponent;

    private Date dateCreated;

    private Boolean isOpen;
    private String code;
    private Boolean isArchive;
    private List<TournamentHero> tournamentHeroes;
    private List<TournamentCreateHero> tournamentCreateHeroes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTournament getOwner() {
        return owner;
    }

    public void setOwner(UserTournament owner) {
        this.owner = owner;
    }

    public UserTournament getOpponent() {
        return opponent;
    }

    public void setOpponent(UserTournament opponent) {
        this.opponent = opponent;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getArchive() {
        return isArchive;
    }

    public void setArchive(Boolean archive) {
        isArchive = archive;
    }

    public List<TournamentHero> getTournamentHeroes() {
        return tournamentHeroes;
    }

    public void setTournamentHeroes(List<TournamentHero> tournamentHeroes) {
        this.tournamentHeroes = tournamentHeroes;
    }

    public List<TournamentCreateHero> getTournamentCreateHeroes() {
        return tournamentCreateHeroes;
    }

    public void setTournamentCreateHeroes(List<TournamentCreateHero> tournamentCreateHeroes) {
        this.tournamentCreateHeroes = tournamentCreateHeroes;
    }
}
