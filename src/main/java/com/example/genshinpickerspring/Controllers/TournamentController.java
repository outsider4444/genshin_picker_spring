package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Models.RequestModels.UserInfo;
import com.example.genshinpickerspring.Models.Tournament;
import com.example.genshinpickerspring.Models.TournamentCreateHero;
import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Services.TournamentService;
import com.example.genshinpickerspring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;

    @GetMapping
    private List<Tournament> getAll(){
        return tournamentService.getAll();
    }

    // TODO
    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestHeader("Authorization") String token,
                                                       @RequestBody Tournament tournament) {
        // Получаем информацию о пользователе по токену
        UserInfo user1 = userService.getUserInfoByToken(token).orElseThrow();
        User user = userService.getUserById(user1.getId()).orElseThrow();

        // Устанавливаем владельца турнира
        tournament.setOwner(user);

        // Генерируем случайный код для турнира
        tournament.setCode(UUID.randomUUID().toString());

        // Устанавливаем, что турнир не в архиве
        tournament.setIsArchive(false);

        // Сохраняем турнир с связанными сущностями
        Tournament createdTournament = tournamentService.saveTournament(tournament);

        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }

}
