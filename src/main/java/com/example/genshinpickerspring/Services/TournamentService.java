package com.example.genshinpickerspring.Services;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Models.Tournament;
import com.example.genshinpickerspring.Models.TournamentCreateHero;
import com.example.genshinpickerspring.Repositories.TournamentCreateHeroRepository;
import com.example.genshinpickerspring.Repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TournamentCreateHeroRepository tournamentCreateHeroRepository;

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

//    public Tournament saveTournament(Tournament tournament) {
        // Сначала сохраняем сам турнир
//        Tournament savedTournament = tournamentRepository.save(tournament);
//
//        // Если в турнире есть список героев для создания, связываем их с турниром и сохраняем
//        if (tournament.getTournamentCreateHeroes() != null) {
//            for (TournamentCreateHero createHero : tournament.getTournamentCreateHeroes()) {
//                createHero.setTournament(savedTournament);
//                 Поскольку cascade = CascadeType.ALL, связанный герой будет сохранен автоматически
//                 Нет необходимости вызывать tournamentCreateHeroRepository.save(createHero);
//            }
//        }
//
//        // Возвращаем сохраненный турнир
//        return savedTournament;
//        return tournamentRepository.save(tournament);
//    }
}
