package springmvc.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.BookDao;
import springmvc.entity.Book;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

    public void addBook(Book book) {

        try {

            Session session = this._sessionFactory.getCurrentSession();
            session.persist(book);
            session.flush();
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("BookDAO could not add new book:" + book);
        }
    }

    public Book getBookById(long id) {

        Book book = null;
        try {

            Session session = this._sessionFactory.getCurrentSession();
            book = session.load(Book.class, new Long(id));
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("BookDAO could not get book by id:" + id);
        }
        if (book == null) {

            throw new Error("Not found book with " + id + " id");
        }
        Hibernate.initialize(book.getAuthors());

        return book;
    }

    public Set<Book> listBooks() {

        Set<Book> books = null;
        try {
            Session session = this._sessionFactory.getCurrentSession();
            books = new HashSet<>(
                    session.createQuery("FROM Book ", Book.class).list()
            );
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("BookDAO could not list books");
        }

        return books;
    }

    public void updateBook(Book book) {

        try {
            Session session = this._sessionFactory.getCurrentSession();
            session.update(book);
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("BookDAO could not update book:" + book);
        }
    }

    public void removeBook(long id) {

        try {

            Session session = this._sessionFactory.getCurrentSession();
            Book book = session.load(Book.class, new Long(id));
            session.delete(book);
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("BookDAO could not delete book with id:" + id);
        }
    }

    @Autowired
    private SessionFactory _sessionFactory;
}
