package vn.techmaster.basicthymeleaf.minh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import vn.techmaster.basicthymeleaf.minh.repository.PersonRepository;

@Controller
@CrossOrigin
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/peopletable")
    public String getPeopleTable(Model model) {
        model.addAttribute("people", personRepository.getPeople());
        return "people-table";
    }

    @GetMapping("/peoplecol")
    public String getPeopleCol(Model model) {
        model.addAttribute("people", personRepository.getPeople());
        return "people-col";
    }
}
