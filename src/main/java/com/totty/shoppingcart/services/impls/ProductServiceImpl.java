package com.totty.shoppingcart.services.impls;

import com.totty.shoppingcart.exceptions.ProductNotFoundException;
import com.totty.shoppingcart.models.request.AddProductRequest;
import com.totty.shoppingcart.models.Category;
import com.totty.shoppingcart.models.Product;
import com.totty.shoppingcart.models.request.UpdateProductRequest;
import com.totty.shoppingcart.repositories.CategoryRepository;
import com.totty.shoppingcart.repositories.ProductRepository;
import com.totty.shoppingcart.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest productRequest) {
        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory().getName()))
                .orElseGet(() -> {
                            Category newCategory = new Category(productRequest.getCategory().getName());
                            return categoryRepository.save(newCategory);
                        }
                );
        productRequest.setCategory(category);

        return productRepository.save(createProduct(productRequest, category));
    }

    private Product createProduct(AddProductRequest productRequest, Category category) {
        return new Product(
                productRequest.getName(),
                productRequest.getBrand(),
                productRequest.getPrice(),
                productRequest.getDescription(),
                productRequest.getInventory(),
                category
        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(request.getName());
                    existingProduct.setBrand(request.getBrand());
                    existingProduct.setPrice(request.getPrice());
                    existingProduct.setDescription(request.getDescription());
                    existingProduct.setInventory(request.getInventory());

                    Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                            .orElseGet(() -> {
                                Category newCategory = new Category();
                                newCategory.setName(request.getCategory().getName());
                                return categoryRepository.save(newCategory);
                            });

                    existingProduct.setCategory(category);

                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", id)));
    }



    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ProductNotFoundException(String.format("Product with id %s not found", id));
                });
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        //TODO: Create later custom exception for handling errors
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("Product with id: %d is not found!", id)));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String productName) {
        return productRepository.findByBrandAndName(brand, productName);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String productName) {
        return productRepository.countByBrandAndName(brand, productName);
    }
}
