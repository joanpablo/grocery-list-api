package com.rideco.grocery.services;

import com.rideco.grocery.exceptions.ProductNotFoundException;
import com.rideco.grocery.models.Product;
import com.rideco.grocery.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service that exposes methods for saving and getting Product data.
 */
@Component
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(final ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Gets all products.
     *
     * @return a collection of all products.
     */
    public Iterable<Product> findAll() {
        return this.productsRepository.findAllByOrderByIdDesc();
    }

    /**
     * Saves a product.
     * If the product exists it updates the product, otherwise it creates a new one and assign a new ID.
     *
     * @param product the product to create or update.
     * @return a new instance of the saved product.
     */
    public Product save(final Product product) {
        return this.productsRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete.
     */
    public void delete(final Integer productId) {
        this.productsRepository.deleteById(productId);
    }

    public Product findById(final Integer productId) throws ProductNotFoundException {
        return this.productsRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
