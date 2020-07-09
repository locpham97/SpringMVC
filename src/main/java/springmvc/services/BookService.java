package springmvc.services;


import springmvc.entity.Book;

import java.util.Set;

public interface BookService {

    public void addBook(Book book);

    public Book getBookById(long id);

    public Set<Book> listBooks();

    public void removeBook(long id);

    public void updateBook(Book book);
}
