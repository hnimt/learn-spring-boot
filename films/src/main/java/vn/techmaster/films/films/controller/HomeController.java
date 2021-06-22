package vn.techmaster.films.films.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.films.films.model.Film;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String showHomePage(Model model) {
        model.addAttribute("name", "Minh");
        return "index";
    }

    @GetMapping("/film")
    public String listFilms(Model model) {
        List<Film> films = List.of(new Film("Harry Potter", "World of wizzard", "JK.Rowling", 1996),
                new Film("Tom and Jerry", "GAFAF ASDASD", "Tom", 1995),
                new Film("Avatar", "QWEQE ASDASD", "James", 2005),
                new Film("Avenger:End Game", "Avenger ", "John", 2019),
                new Film("Spiderman:Come back home", "Spiderman", "James", 2019));

        model.addAttribute("films", films);
        return "films";
    }

}
