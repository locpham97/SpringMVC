package springmvc.dao.impl;

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

    public void setSessionFactory(SessionFactory sf) {

        this._sessionFactory = sf;
    }

    public void addBook(Book book) {

        Session session = this._sessionFactory.getCurrentSession();
        session.persist(book);
    }

    public Book getBookById(long id) {

        Session session = this._sessionFactory.getCurrentSession();
        Book b = session.load(Book.class, new Long(id));
        if (b == null) {

            throw new Error("Not found book with " + id + " id");
        }

        return null;
    }

    public Set<Book> listBooks() {

        Session session = this._sessionFactory.getCurrentSession();
        Set<Book> booksList = new HashSet<>(
            session.createQuery("from Book ", Book.class).list()
        );

        return booksList;
    }

    public void updateBook(Book book) {

        Session session = this._sessionFactory.getCurrentSession();
        session.update(book);
    }

    public void removeBook(long id) {

        Session session = this._sessionFactory.getCurrentSession();
        Book b = session.load(Book.class, new Long(id));
        if(b != null){

            session.delete(b);
        }
    }

    @Autowired
    private SessionFactory _sessionFactory;
}
