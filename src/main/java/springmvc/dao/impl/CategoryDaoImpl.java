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

        try {

            Session session = this._sessionFactory.getCurrentSession();
            session.persist(category);
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("CategoryDAO could not add category:" + category);
        }
    }

    @Override
    public Category getCategoryById(long id) {

        Category category = null;
        try {
            Session session = this._sessionFactory.getCurrentSession();
            category = session.load(Category.class, new Long(id));
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("Category could not get category" +
                    " with id :" + id);
        }
        if (category == null) {
            throw new Error("Not found category");
        }

        return category;
    }

    @Override
    public Set<Category> listCategories() {

        Set<Category> categories = null;
        try {

            Session session = this._sessionFactory.getCurrentSession();
            categories = new HashSet<>(
                    session.createQuery("from Category", Category.class).list()
            );
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new Error("CategoryDAO could not list categories");
        }

        return categories;
    }

    @Autowired
    private SessionFactory _sessionFactory;
}
