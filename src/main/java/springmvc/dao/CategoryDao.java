package springmvc.dao;


import springmvc.entity.Category;

import java.util.Set;

public interface CategoryDao {

    public void addCategory(Category category);

    public Set<Category> listCategories();

    public Category getCategoryById(long id);
}
