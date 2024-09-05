package com.totty.shoppingcart.services.impls;

import com.totty.shoppingcart.models.Category;
import com.totty.shoppingcart.repositories.CategoryRepository;
import com.totty.shoppingcart.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<Category> getCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return null;
    }

    @Override
    public void deleteCategory(Category category) {

    }
}
