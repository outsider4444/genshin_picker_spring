package com.example.genshinpickerspring.Services;

import com.example.genshinpickerspring.Models.Hero;
import com.example.genshinpickerspring.Repositories.HeroRepository;
import com.example.genshinpickerspring.Services.HeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class HeroServiceTest {

    @Autowired
    private HeroService heroService;

    @MockBean
    private HeroRepository heroRepository;

    private Hero hero;

    @BeforeEach
    public void setUp() {
        hero = new Hero();
        hero.setId(1L);
        hero.setName("Test Hero");
        hero.setPhotoPath("/images/test.jpg");
        hero.setStarCount(5);
    }

    @Test
    public void testGetAllHeroes() {
        List<Hero> heroes = Arrays.asList(hero);

        // Мокируем вызов метода findAll() репозитория
        when(heroRepository.findAll()).thenReturn(heroes);

        // Тестируем метод сервиса getAll()
        System.out.println("Running testGetAllHeroes...");
        List<Hero> result = heroService.getAll();

        // Проверки и выводы
        assertEquals(1, result.size());
        System.out.println("Number of heroes: " + result.size());
        assertEquals("Test Hero", result.get(0).getName());
        System.out.println("First hero's name: " + result.get(0).getName());
        System.out.println("testGetAllHeroes passed!");
    }

    @Test
    public void testSaveHero() {
        // Мокируем вызов метода save() репозитория с указанием типа Hero
        when(heroRepository.save(hero)).thenReturn(hero);

        // Тестируем метод сервиса addHero()
        System.out.println("Running testSaveHero...");
        Hero savedHero = heroService.addHero(hero);

        // Проверки и выводы
        assertEquals("Test Hero", savedHero.getName());
        System.out.println("Saved hero's name: " + savedHero.getName());
        verify(heroRepository, times(1)).save(hero);
        System.out.println("testSaveHero passed!");
    }


    @Test
    public void testGetHeroById() {
        // Мокируем вызов метода findById() репозитория
        when(heroRepository.findById(1L)).thenReturn(Optional.of(hero));

        // Тестируем метод сервиса getHeroById()
        System.out.println("Running testGetHeroById...");
        Optional<Hero> foundHero = heroService.getHeroById(1L);

        // Проверки и выводы
        assertTrue(foundHero.isPresent());
        System.out.println("Hero found: " + foundHero.isPresent());
        assertEquals("Test Hero", foundHero.get().getName());
        System.out.println("Found hero's name: " + foundHero.get().getName());
        System.out.println("testGetHeroById passed!");
    }

    @Test
    public void testDeleteHero() {
        // Мокируем вызов метода deleteById() репозитория
        doNothing().when(heroRepository).deleteById(1L);

        // Тестируем метод сервиса deleteHero()
        System.out.println("Running testDeleteHero...");
        heroService.deleteHero(1L);

        // Проверки и выводы
        verify(heroRepository, times(1)).deleteById(1L);
        System.out.println("Hero deleted with ID: " + 1L);
        System.out.println("testDeleteHero passed!");
    }
}
