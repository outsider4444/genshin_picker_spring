package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Models.RequestModels.UserInfo;
import com.example.genshinpickerspring.Models.UsersHero;
import com.example.genshinpickerspring.Services.UserHeroService;
import com.example.genshinpickerspring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users_hero")
public class UsersHeroController {
    @Autowired
    private UserHeroService userHeroService;
    @Autowired
    private UserService userService;

    @PostMapping
    public UsersHero addUsersHero(@RequestHeader("Authorization") String token, @RequestBody UsersHero hero) {
        UserInfo userInfo = userService.getUserInfoByToken(token).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userService.getUserById(userInfo.getId()).orElseThrow();

        var userHeroes = user.getUserHeroes();
        userHeroes.addLast(hero);
        user.setUserHeroes(userHeroes);
        userHeroService.addUsersHero(hero);

        return hero;
    }

    @PutMapping("/{heroId}/increase")
    public ResponseEntity<UserInfo> increaseUserHero(@PathVariable Long heroId, @RequestHeader("Authorization") String token){

        UserInfo userInfo = userService.getUserInfoByToken(token).orElseThrow(() -> new RuntimeException("User not found"));
        UsersHero usersHero = userHeroService.getUsersHeroById(heroId).orElseThrow();

        userHeroService.increaseConstUsersHero(usersHero);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{heroId}")
    public ResponseEntity<UserInfo> increaseUserHero(@PathVariable Long heroId,
            @RequestBody UsersHero hero, @RequestHeader("Authorization") String token){

        UserInfo userInfo = userService.getUserInfoByToken(token).orElseThrow(() -> new RuntimeException("User not found"));
        UsersHero usersHero = userHeroService.getUsersHeroById(heroId).orElseThrow();
        System.out.println(hero.getLevel());

        userHeroService.increaseLevelUsersHero(usersHero, hero.getLevel());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{heroId}")
    public ResponseEntity<UserInfo> deleteUsersHero(@PathVariable Long heroId, @RequestHeader("Authorization") String token) {
        UserInfo userInfo = userService.getUserInfoByToken(token).orElseThrow(() -> new RuntimeException("User not found"));
        UsersHero usersHero = userHeroService.getUsersHeroById(heroId).orElseThrow();

        userHeroService.deleteUsersHero(usersHero.getId());

        return ResponseEntity.noContent().build();
    }
}
