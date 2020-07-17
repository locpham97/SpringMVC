package springmvc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.CategoryDao;
import springmvc.entity.Category;
import springmvc.services.CategoryService;

import java.util.Set;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Override
    public void addCategory(Category category) {

        this.categoryDao.addCategory(category);
    }

    @Override
    public Category getCategoryById(long id) {

        return this.categoryDao.getCategoryById(id);
    }

    @Override
    public Set<Category> listCategories() {

        return this.categoryDao.listCategories();
    }


    @Autowired
    private CategoryDao categoryDao;
}
