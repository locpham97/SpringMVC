package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.CategoryDao;
import springmvc.entity.Category;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void addCategory(Category category) {

        Session session = this._sessionFactory.getCurrentSession();
        session.persist(category);
    }

    @Override
    public Category getCategoryById(long id) {
        Session session = this._sessionFactory.getCurrentSession();
        Category category  = session.load(Category.class, new Long(id));
        if(category == null){
            throw new Error("Cannot find id category");
        }
        return category;
    }

    @Override
    public Set<Category> listCategories() {

        Session session = this._sessionFactory.getCurrentSession();
        Set<Category> categoriesList = new HashSet<>(
                session.createQuery("from Category", Category.class).list()
        );
        return categoriesList;
    }

    @Autowired
    private SessionFactory _sessionFactory;
}
