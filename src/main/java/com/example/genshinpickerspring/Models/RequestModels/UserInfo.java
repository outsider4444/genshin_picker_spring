package com.example.genshinpickerspring.Models.RequestModels;


import com.example.genshinpickerspring.Models.Role;
import com.example.genshinpickerspring.Models.UsersHero;

import java.util.List;
import java.util.Set;

// Модель для отображения информации о пользователе
public class UserInfo{
    private Long id;
    private String email;
    private String username;
    private Role role;
    private List<UsersHero> userCharacters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<UsersHero> getUserCharacters() {
        return userCharacters;
    }

    public void setUserCharacters(List<UsersHero> userCharacters) {
        this.userCharacters = userCharacters;
    }
}
