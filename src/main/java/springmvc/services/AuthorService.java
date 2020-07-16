package springmvc.services;

import springmvc.entity.Author;
import springmvc.entity.Book;

import java.util.Set;

public interface AuthorService {
    public void addAuthor(Author author);

    public Author getAuthorById(long id);

    public Set<Author> listAuthors();

    public void removeAuthor(long id);

    public void updateAuthor(Author author);

    public void addBook(long authorId, Book book);
}
