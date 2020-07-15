package springmvc.controllers;

import jdk.nashorn.api.scripting.JSObject;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springmvc.entity.Author;
import springmvc.entity.Book;
import springmvc.entity.Category;
import springmvc.services.AuthorService;
import springmvc.services.BookService;
import springmvc.services.CategoryService;

import java.util.*;

@Controller
@RequestMapping("/book")
@SessionAttributes
public class BookController {

    @GetMapping(value = "/")
    public String listBooks(Model model) {

        model.addAttribute("listBook", this._bookService.listBooks());

        return "book";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam("name") String name,@RequestParam("category") long idCategory,@RequestParam("authors") String[] authorIds) {

        Book newBook = new Book();
        Category category = _categoryService.getCategoryById(idCategory);
        newBook.setCategory(category);
        newBook.setName(name);
        Set<Author> newAuthors = new HashSet<>();
        _bookService.addBook(newBook);
        for(String strIdAuthor:authorIds){
            Author author = _authorService.getAuthorById(Long.parseLong(strIdAuthor));
            author.addBook(newBook);
            newAuthors.add(author);
            _authorService.updateAuthor(author);
        }

        return "redirect:/book/";
    }

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Set.class, "authors", new CustomCollectionEditor(Set.class)
//        {
//            @Override
//            protected Object convertElement(Object element)
//            {
//
//                System.out.println("ABC");
//                Long id = null;
//
//                if(element instanceof String && !((String)element).equals("")){
//                    try{
//                        id = Long.parseLong((String) element);
//                    }
//                    catch (NumberFormatException e) {
//                        System.out.println("Element was " + ((String) element));
//                        e.printStackTrace();
//                    }
//                }
//                else if(element instanceof Long) {
//                    id = (Long) element;
//                }
//
//                return id != null ? _authorService.getAuthorById(id) : null;
//            }
//        });
//    }

    @GetMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") long id) {

        this._bookService.removeBook(id);

        return "redirect:/book/";
    }

    @GetMapping("/showAdd")
    public String showAdd(Model model) {

        Set<Author> listOfAuthors = this._authorService.listAuthors();
        Set<Category> listOfCategories = this._categoryService.listCategories();
        model.addAttribute("listOfAuthors", listOfAuthors);
        model.addAttribute("listOfCategories", listOfCategories);
        model.addAttribute("edit",false);

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
