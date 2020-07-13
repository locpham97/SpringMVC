package springmvc.services;

import springmvc.entity.Category;

import java.util.Set;


public interface CategoryService {

    public void addCategory(Category category);
    public Category getCategoryById(long id);
    public Set<Category> listCategories();
}
