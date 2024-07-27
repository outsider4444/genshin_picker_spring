package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        return heroService.getHeroById(id)
                .map(Hero -> ResponseEntity.ok().body(Hero))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hero createHero(@RequestBody Hero Hero) {
        return heroService.addHero(Hero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @RequestBody Hero HeroDetails) {
        return ResponseEntity.ok(heroService.updateHero(id, HeroDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable Long id) {
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }
}
