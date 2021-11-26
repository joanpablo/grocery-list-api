package com.rideco.grocery.controllers;

import com.rideco.grocery.models.Product;
import com.rideco.grocery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

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
     * @return the collection of all products.
     */
    @GetMapping
    public List<Product> findAll() {
        return this.productsService.findAll();
    }

    /**
     * Saves a new product.
     * @param product the product to save.
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
     * @param product the product to update.
     * @return an instance of the product updated.
     */
    @PutMapping
    public Product update(@RequestBody Product product) {
        return this.productsService.update(product);
    }

    @DeleteMapping
    public void delete(@PathParam("id") Integer productId) {
        this.productsService.delete(productId);
    }
}
