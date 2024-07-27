package com.example.genshinpickerspring.Services;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.UsersHero;
import com.example.genshinpickerspring.Repositories.UsersHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserHeroService {
    @Autowired
    private UsersHeroRepository usersHeroRepository;

    public Optional<UsersHero> getUsersHeroById(Long id) {
        return usersHeroRepository.findById(id);
    }

    public UsersHero addUsersHero(UsersHero  usersHero) {
        return usersHeroRepository.save(usersHero);
    }

    public void deleteUsersHero(Long id) {
        usersHeroRepository.deleteById(id);
    }
}
