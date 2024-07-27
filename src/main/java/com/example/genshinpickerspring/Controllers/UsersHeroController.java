package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Models.UserInfo;
import com.example.genshinpickerspring.Models.UsersHero;
import com.example.genshinpickerspring.Repositories.UserRepository;
import com.example.genshinpickerspring.Services.HeroService;
import com.example.genshinpickerspring.Services.UserHeroService;
import com.example.genshinpickerspring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users_hero")
public class UsersHeroController {
    @Autowired
    private UserHeroService userHeroService;
    @Autowired
    private UserService userService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public UserInfo addUsersHero(@RequestHeader("Authorization") String token, @RequestBody UsersHero hero) {
        UserInfo userInfo = userService.getUserInfoByToken(token).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userService.getUserById(userInfo.getId()).orElseThrow();

        var userHeroes = user.getUserHeroes();
        userHeroes.addLast(hero);
        user.setUserHeroes(userHeroes);
        userHeroService.addUsersHero(hero);

        return userInfo;
    }

    @DeleteMapping("/{heroId}")
    public ResponseEntity<UserInfo> deleteUsersHero(@PathVariable Long heroId, @RequestHeader("Authorization") String token) {
        UserInfo userInfo = userService.getUserInfoByToken(token).orElseThrow(() -> new RuntimeException("User not found"));
        UsersHero usersHero = userHeroService.getUsersHeroById(heroId).orElseThrow();

        userHeroService.deleteUsersHero(usersHero.getId());

        return ResponseEntity.noContent().build();
    }
}
