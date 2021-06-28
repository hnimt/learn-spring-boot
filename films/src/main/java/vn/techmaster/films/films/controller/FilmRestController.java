package vn.techmaster.films.films.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.films.films.model.Film;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FilmRestController {

    @GetMapping("/films")
    public ResponseEntity<List<Film>> listFilms() {
        List<Film> films = List.of(new Film("Harry Potter", "World of wizzard", "JK.Rowling", 1996),
                new Film("Tom and Jerry", "GAFAF ASDASD", "Tom", 1995),
                new Film("Avatar", "QWEQE ASDASD", "James", 2005),
                new Film("Avenger:End Game", "Avenger ", "John", 2019),
                new Film("Spiderman:Come back home", "Spiderman", "James", 2019));

        return ResponseEntity.ok().body(films);
    }
}
