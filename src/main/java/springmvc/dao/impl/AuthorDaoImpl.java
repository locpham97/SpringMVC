package springmvc.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.AuthorDao;
import springmvc.entity.Author;
import springmvc.entity.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    @Autowired
    private SessionFactory _sessionFactory;

    @Override
    public void addAuthor(Author author) {

        try {
            Session session = this._sessionFactory.getCurrentSession();
            session.persist(author);
            session.flush();
        }
        catch (Exception e) {
            throw new Error("AuthorDAO could not add author:" + author);
        }

    }

    @Override
    public void addBook(List<Long> authorIds, Book book) {

        try {

            Session session = this._sessionFactory.getCurrentSession();
            String query = "FROM Author author WHERE author.id IN :ids";
            ArrayList<Author> authors = new ArrayList<>(session
                                    .createQuery(query)
                                    .setParameterList("ids",authorIds)
                                    .list());

            for(Author author: authors){
                boolean isBelongAuthor = author.getBooks()
                                        .stream()
                                        .anyMatch(b-> b.getId() == book.getId());
                if(isBelongAuthor){

                    continue;
                }
                author.addBook(book);
            }
            session.flush();
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("AuthorDAO could not added book with " + book
                    + " authors with " + authorIds);
        }
    }

    @Override
    public Author getAuthorById(long id) {

        Author a = null;
        try {

            Session session = this._sessionFactory.getCurrentSession();
            a = session.load(Author.class, new Long(id));
            session.flush();
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("AuthorDAO could not get author with:" + id);
        }

        if (a == null) {

            throw new Error("Not found author with " + id + " id");
        }

        return a;
    }

    @Override
    public Set<Author> listAuthors() {

        Set<Author> authors = null;
        try {

            Session session = this._sessionFactory.getCurrentSession();
            authors = new HashSet<>(
                    session.createQuery("FROM Author", Author.class).list()
            );
            session.flush();
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("AuthorDAO could not list authors");
        }

        return authors;
    }

    @Override
    public void removeAuthor(long id) {

        Author a = null;
        try {

            Session session = this._sessionFactory.getCurrentSession();
            a = session.load(Author.class, new Long(id));
            session.remove(a);
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("AuthorDAO could not delete author with:" + id);
        }
    }

    @Override
    public void updateAuthor(Author author) {

        try {

            Session session = this._sessionFactory.getCurrentSession();
            session.update(author);
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("AuthorDAO could not update author:" + author);
        }
    }
}
