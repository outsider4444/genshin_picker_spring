package com.example.genshinpickerspring.Services;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.Tournament;
import com.example.genshinpickerspring.Repositories.HeroRepository;
import com.example.genshinpickerspring.Repositories.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TournamentServiceTest {
    @Autowired
    private TournamentService tournamentService;

    @MockBean
    private TournamentRepository tournamentRepository;

    private Tournament tournament;

    @BeforeEach
    public void setUp() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
    }
}
