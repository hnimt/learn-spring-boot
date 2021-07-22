package com.minh.Lab10FileUpload.controller;

import com.minh.Lab10FileUpload.entity.Job;
import com.minh.Lab10FileUpload.entity.Person;
import com.minh.Lab10FileUpload.repository.JobDao;
import com.minh.Lab10FileUpload.repository.PersonDao;
import com.minh.Lab10FileUpload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private JobDao jobDao;

    @Autowired
    private StorageService storageService;

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
         model.addAttribute("jobs", jobDao.getAll());
         return "person";
     }

     @PostMapping(value = "/save", consumes = {"multipart/form-data"})
     public String save(@Valid @ModelAttribute Person person, BindingResult result,
                        RedirectAttributes redirect) {

        if (person.getPhoto().getOriginalFilename().isEmpty()){
            result.addError(new FieldError("person", "photo", "Photo is required"));
        }

         if (result.hasErrors()) {
            return "person";
         }
         storageService.uploadFile(person.getPhoto());

         if (person.getId() > 0) {
            personDao.update(person);
         } else {
            personDao.add(person);
         }

         return "redirect:/person";
     }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personDao.get(id);
        if(person.isPresent()){
            String photoName = storageService.getPathName(person);
            String photoPath = "/images/" + photoName;
            System.out.println(photoPath);
            model.addAttribute("person", person);
            model.addAttribute("photoPath", photoPath);
            model.addAttribute("jobs", jobDao.getAll());
        }
        return "person";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") int id, Model model){
        personDao.deleteById(id);
        return "redirect:/person";
    }

    @GetMapping("/jobs")
    public String getJobs(Model model){
        model.addAttribute("jobs", jobDao.getAll());
        model.addAttribute("newJob", new Job());
        return "jobs";
    }

    @PostMapping("/jobs")
    public String addJob(@ModelAttribute Job newJob, BindingResult result, RedirectAttributes redirect){

        if (result.hasErrors()){
            return "redirect:/person/jobs";
        }
        if (newJob.getJobName().isEmpty()){
            return "redirect:/person/jobs";
        }

        for (Job job1 : jobDao.getAll()){
            if (job1.getJobName().equalsIgnoreCase(newJob.getJobName())) {
                return "redirect:/person/jobs";
            }
        }

        jobDao.add(newJob);
        return "redirect:/person/jobs";
    }
}
