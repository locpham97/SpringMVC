package springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.entity.Category;
import springmvc.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {

        this._categoryService.addCategory(category);
        return "redirect:/books/";
    }

    @GetMapping("/showAdd")
    public String showAdd() {

        return "form-add-category";
    }

    @Autowired
    private CategoryService _categoryService;
}
