package vn.techmaster.basicthymeleaf.minh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.basicthymeleaf.minh.request.AddRequest;
import vn.techmaster.basicthymeleaf.minh.response.AddResponse;

@Controller
@RequestMapping("/add")
@CrossOrigin
public class AddController {

    @GetMapping
    public String getAddForm(Model model) {
        model.addAttribute("addRequest", new AddRequest());
        model.addAttribute("addResponse", null);
        return "add";
    }

    @PostMapping()
    public String handleForm(@ModelAttribute AddRequest request, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            int result = request.getA() + request.getB();
            AddResponse addResponse = new AddResponse(result);
            model.addAttribute("addRequest", request);
            model.addAttribute("addResponse", addResponse);
        }
        return "add";
    }
}
