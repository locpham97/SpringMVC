package springmvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.entity.Book;
import springmvc.services.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public String listBooks(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("listBook", this.bookService.listBooks());
        return "book";
    }
}
