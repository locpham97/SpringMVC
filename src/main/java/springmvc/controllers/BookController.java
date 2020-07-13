package springmvc.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.entity.Author;
import springmvc.entity.Book;
import springmvc.entity.Category;
import springmvc.services.AuthorService;
import springmvc.services.BookService;
import springmvc.services.CategoryService;

import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping(value = "/")
    public String listBooks(Model model) {

        model.addAttribute("listBook", this._bookService.listBooks());
        return "book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {

        this._bookService.addBook(book);
        return "redirect:/book/";
    }

    @GetMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") long id) {

        this._bookService.removeBook(id);
        return "redirect:/book/";
    }

    @GetMapping("/showAdd")
    public String showAdd(Model model) {

        Book newBook = new Book();
        Set<Author> authors = this._authorService.listAuthors();
        Set<Category> categories = this._categoryService.listCategories();
        Hibernate.initialize(newBook.getAuthors());
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("edit",false);
        model.addAttribute("book",newBook);
        return "form-book";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@ModelAttribute("book") Book book) {

        this._bookService.updateBook(book);
        return "redirect:/book/";
    }

    @Autowired
    private BookService _bookService;
    @Autowired
    private AuthorService _authorService;
    @Autowired
    private CategoryService _categoryService;
}
