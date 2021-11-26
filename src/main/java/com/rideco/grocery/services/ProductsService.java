package com.rideco.grocery.services;

import com.rideco.grocery.models.Product;
import com.rideco.grocery.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service that exposes methods for saving and getting Product data.
 */
@Component
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Gets all products.
     * @return a collection of all products.
     */
    public List<Product> findAll() {
        return this.productsRepository.findAll();
    }

    /**
     * Saves a product.
     * @param product the product to save.
     * @return a new instance of the product saved with an ID.
     */
    public Product save(Product product) {
        return this.productsRepository.save(product);
    }

    /**
     * Updates a product.
     * @param product the product to update.
     * @return an instance of the product updated.
     */
    public Product update(Product product) {
        return this.productsRepository.save(product);
    }
}
