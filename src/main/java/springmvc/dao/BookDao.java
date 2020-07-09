package springmvc.dao;


import springmvc.entity.Book;

import java.util.Set;

public interface BookDao {
    public void addBook(Book book);
    public void updateBook(Book book);
    public Set<Book> listBooks();
    public Book getBookById(long id);
    public void removeBook(long id);
}
