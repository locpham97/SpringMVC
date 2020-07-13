package springmvc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.AuthorDao;
import springmvc.dao.BookDao;
import springmvc.entity.Book;
import springmvc.services.BookService;

import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Transactional
    public void addBook(Book book) {

        this.bookDao.addBook(book);
    }

    @Transactional
    public Book getBookById(long id) {

        return this.bookDao.getBookById(id);
    }

    @Transactional
    public Set<Book> listBooks() {

        return this.bookDao.listBooks();
    }

    @Transactional
    public void removeBook(long id) {

        this.bookDao.removeBook(id);
    }

    public void setBookDAO(BookDao bookDAO) {

        this.bookDao = bookDAO;
    }

    @Transactional
    public void updateBook(Book book) {

        this.bookDao.updateBook(book);
    }

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
}
