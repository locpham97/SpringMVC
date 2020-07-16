package springmvc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.dao.AuthorDao;
import springmvc.entity.Author;
import springmvc.entity.Book;
import springmvc.services.AuthorService;

import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Override
    public void addAuthor(Author author) {

        this.authorDao.addAuthor(author);
    }

    @Override
    public Author getAuthorById(long id) {

        return this.authorDao.getAuthorById(id);
    }

    @Override
    public Set<Author> listAuthors() {

        return this.authorDao.listAuthors();
    }

    @Override
    public void removeAuthor(long id) {

    }

    @Override
    public void updateAuthor(Author author) {

        this.authorDao.updateAuthor(author);
    }

    @Override
    public void addBook(long authorId, Book book) {

        this.authorDao.addBook(authorId, book);
    }

    @Autowired
    private AuthorDao authorDao;
}
