package springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.entity.Author;
import springmvc.services.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute("author") Author author){
        this._authorService.addAuthor(author);
        return "redirect:/book/";
    }

    @GetMapping("/showAdd")
    public String showAdd(){

        return "form-add-author";
    }
    @Autowired
    private AuthorService _authorService;
}
