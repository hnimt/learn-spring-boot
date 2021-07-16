package com.minh.lab08crud.controller;

import java.util.Optional;

import com.minh.lab08crud.model.Person;
import com.minh.lab08crud.repository.PersonDao;
import com.minh.lab08crud.request.SearchRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("people", personDao.getAll());
        return "allpeople";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personDao.get(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        }
        return "person";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Person person, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        if (person.getId() > 0) {
            personDao.update(person);
        } else {
            personDao.add(person);
        }

        return "redirect:/person";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteByID(@PathVariable("id") int id) {
        personDao.deleteByID(id);
        return "redirect:/person";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPersonId(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personDao.get(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        }
        return "form";
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("searchrequest", new SearchRequest());
        return "search";
    }

    @PostMapping("/search")
    public String searchByKeyword(@ModelAttribute SearchRequest request, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasFieldErrors()) {
            model.addAttribute("people", personDao.searchByKeyword(request.getKeyword()));
        }
        return "allpeople";
    }

}
