package springmvc.controllers;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
@SessionAttributes
public class BookController {

    @GetMapping(value = "/")
    public String listBooks(Model model) {

        model.addAttribute("listBook", this._bookService.listBooks());

        return "book";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam("name") String name,
                          @RequestParam("category") long idCategory,
                          @RequestParam("authors") List<String> strAuthorIds) {

        Category category = _categoryService.getCategoryById(idCategory);
        Book book = new Book(category,name);
        _bookService.addBook(book);
        List<Long> authorIds = strAuthorIds
                                        .stream()
                                        .map(Long::valueOf)
                                        .collect(Collectors.toList());
        _authorService.addBook(authorIds, book);

        return "redirect:/books/";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") long id,
                           @RequestParam("name") String name,
                           @RequestParam("category") long idCategory,
                           @RequestParam("authors") ArrayList<String> strAuthorIds) {

        Book book = _bookService.getBookById(id);
        book.setName(name);
        Category category = _categoryService.getCategoryById(idCategory);
        book.setCategory(category);
        _bookService.updateBook(book);
        List<Author> oldAuthors = new ArrayList<>(book.getAuthors());
        List<Long> authorIds = strAuthorIds
                                    .stream()
                                    .map(Long::valueOf)
                                    .collect(Collectors.toList());
        _authorService.addBook(authorIds, book);
        for (Author author : oldAuthors) {

            if (authorIds.contains(author.getId())) {

                continue;
            }
            author.removeBook(book);
            _authorService.updateAuthor(author);
        }

        return "redirect:/books/";
    }

    @GetMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") long id) {

        this._bookService.removeBook(id);

        return "redirect:/books/";
    }

    @GetMapping("/showAdd")
    public String showAdd(Model model) {

        Set<Author> listOfAuthors = this._authorService.listAuthors();
        Set<Category> listOfCategories = this._categoryService.listCategories();
        model.addAttribute("listOfAuthors", listOfAuthors);
        model.addAttribute("listOfCategories", listOfCategories);
        model.addAttribute("edit", false);

        return "form-book";
    }

    @GetMapping("/showEdit/{id}")
    public String showEdit(Model model, @PathVariable("id") long id) {

        Book updatedBook = _bookService.getBookById(id);
        Set<Author> listOfAuthors = this._authorService.listAuthors();
        Set<Category> listOfCategories = this._categoryService.listCategories();
        model.addAttribute("listOfAuthors", listOfAuthors);
        model.addAttribute("listOfCategories", listOfCategories);
        model.addAttribute("book", updatedBook);
        model.addAttribute("edit", true);

        return "form-book";
    }

    @Autowired
    private BookService _bookService;

    @Autowired
    private AuthorService _authorService;

    @Autowired
    private CategoryService _categoryService;
}
