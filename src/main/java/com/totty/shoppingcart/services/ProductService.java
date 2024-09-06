package com.totty.shoppingcart.services;

import com.totty.shoppingcart.models.request.AddProductRequest;
import com.totty.shoppingcart.entities.Product;
import com.totty.shoppingcart.models.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest productRequest);

    Product updateProduct(UpdateProductRequest updateProductRequest, Long id);

    void deleteProduct(Long id);

    List<Product> getProducts();

    Product getProduct(Long id);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductByBrand(String brand);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String productName);

    List<Product> getProductsByBrandAndName(String brand, String productName);

    Long countProductsByBrandAndName(String brand, String productName);
}
