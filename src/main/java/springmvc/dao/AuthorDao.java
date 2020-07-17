package springmvc.dao;

import springmvc.entity.Author;
import springmvc.entity.Book;

import java.util.List;
import java.util.Set;

public interface AuthorDao {

    public void addAuthor(Author author);

    public void addBook(List<Long> authorIds, Book book);

    public Author getAuthorById(long id);

    public Set<Author> listAuthors();

    public void removeAuthor(long id);

    public void updateAuthor(Author author);


}
