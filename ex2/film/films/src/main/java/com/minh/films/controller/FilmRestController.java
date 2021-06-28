package com.minh.films.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minh.films.model.Film;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FilmRestController {

    @GetMapping("/films")
    public ResponseEntity<List<Film>> listFilms() {
        List<Film> films = List.of(new Film("Gone with the Wind", "GAFAF ASDASD", "Trấn Thành", 2020),
                new Film("Bố Già", "QWEQE ASDASD", "James", 2005),
                new Film("Parasite", "adasdasdasd", "Bong Joon-ho", 2019),
                new Film("Money Heist", "Spiderman", "Álex Pina", 2018));

        return ResponseEntity.ok().body(films);
    }
}
