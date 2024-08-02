package com.example.genshinpickerspring.Services;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.RequestModels.TournamentHeroInfo;
import com.example.genshinpickerspring.Models.RequestModels.UserInfo;
import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;


    public List<Hero> getAll() {
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


    public List<TournamentHeroInfo> convertToTournamentHeroInfoModel(List<Hero> heroes) {
        List<TournamentHeroInfo> tournamentHeroInfos = new ArrayList<TournamentHeroInfo>();
        for (Hero hero: heroes) {
            TournamentHeroInfo heroInfo = new TournamentHeroInfo();
            heroInfo.setId(hero.getId());
            heroInfo.setElementType(hero.getElementType());
            heroInfo.setName(hero.getName());
            heroInfo.setEnabled(true);
            heroInfo.setPhotoPath(hero.getPhotoPath());
            heroInfo.setStarCount(hero.getStarCount());
            tournamentHeroInfos.add(heroInfo);
        }
        return tournamentHeroInfos;
    }
}
