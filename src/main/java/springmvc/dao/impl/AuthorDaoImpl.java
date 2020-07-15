package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.AuthorDao;
import springmvc.entity.Author;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    @Autowired
    private SessionFactory _sessionFactory;

    @Override
    public void addAuthor(Author author) {

        Session session = this._sessionFactory.getCurrentSession();
        session.persist(author);
    }

    @Override
    public Author getAuthorById(long id) {

        Session session = this._sessionFactory.getCurrentSession();
        Author a = session.load(Author.class, new Long(id));
        if (a == null) {

            throw new Error("Not found author with " + id + " id");
        }

        return a;
    }

    @Override
    public Set<Author> listAuthors() {

        Session session = this._sessionFactory.getCurrentSession();
        Set<Author> authorsList = new HashSet<>(
                session.createQuery("from Author", Author.class).list()
        );

        return authorsList;
    }

    @Override
    public void removeAuthor(long id) {

        Session session = this._sessionFactory.getCurrentSession();
        Author a = session.load(Author.class, new Long(id));
        if (a != null) {

            session.delete(a);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        Session session = this._sessionFactory.getCurrentSession();
        session.update(author);
    }
}
