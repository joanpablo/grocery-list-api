package com.rideco.grocery.controllers;

import com.rideco.grocery.models.Product;
import com.rideco.grocery.models.RequestProductUpdate;
import com.rideco.grocery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Rest controller that handles http request for getting and saving Product data.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * Gets all products.
     *
     * @return the collection of all products.
     */
    @GetMapping
    public Iterable<Product> findAll() {
        return this.productsService.findAll();
    }

    /**
     * Saves a new product.
     *
     * @param product              the product to save.
     * @param uriComponentsBuilder an instance of a builder for building URI.
     * @return an instance of the created Product.
     */
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder) {
        Product savedProduct = this.productsService.save(product);

        URI location = uriComponentsBuilder
                .path("/products/")
                .path(product.getId().toString())
                .build()
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedProduct);
    }

    /**
     * Updates a Product.
     *
     * @param productId            the ID of the product to update
     * @param requestProductUpdate the properties of the product to update.
     * @return an instance of the product updated.
     */
    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Integer productId, @RequestBody RequestProductUpdate requestProductUpdate) {
        Product product = this.productsService.findById(productId);
        product.setName(requestProductUpdate.getName());
        product.setDescription(requestProductUpdate.getDescription());

        return this.productsService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer productId) {
        this.productsService.delete(productId);
    }
}
