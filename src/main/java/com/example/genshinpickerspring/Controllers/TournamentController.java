package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Models.Tournament;
import com.example.genshinpickerspring.Services.TournamentService;
import com.example.genshinpickerspring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
