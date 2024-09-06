package com.totty.shoppingcart.services.impls;

import com.totty.shoppingcart.exceptions.CategoryAlreadyExistsException;
import com.totty.shoppingcart.exceptions.CategoryNotFoundException;
import com.totty.shoppingcart.entities.Category;
import com.totty.shoppingcart.repositories.CategoryRepository;
import com.totty.shoppingcart.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id %s not found", id)));
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new CategoryAlreadyExistsException(String.format("Category with name %s already exists", category.getName())));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(
                        oldCategory -> {
                            oldCategory.setName(category.getName());
                            return categoryRepository.save(oldCategory);
                        }
                ).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id %s not found", id)));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
            throw new CategoryNotFoundException(String.format("Category with id %s not found", id));
        });
    }
}
