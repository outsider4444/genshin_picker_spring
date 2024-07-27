package com.example.genshinpickerspring.Services;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Repositories.HeroRepository;
import com.example.genshinpickerspring.Repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }
    public Optional<Hero> getHeroById(Long id) {
        return heroRepository.findById(id);
    }
    public Hero addHero(Hero Hero) {
        return heroRepository.save(Hero);
    }
    public Hero updateHero(Long id, Hero HeroDetails) {
        Hero Hero = heroRepository.findById(id).orElseThrow();
        Hero.setName(HeroDetails.getName());
        Hero.setPhotoPath(HeroDetails.getPhotoPath());
        Hero.setStarCount(HeroDetails.getStarCount());
        return heroRepository.save(Hero);
    }
    public void deleteHero(Long id) {
        heroRepository.deleteById(id);
    }
}
