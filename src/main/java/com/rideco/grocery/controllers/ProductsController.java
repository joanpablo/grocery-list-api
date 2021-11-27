package com.rideco.grocery.controllers;

import com.rideco.grocery.converters.SaveProductRequestToProductConverter;
import com.rideco.grocery.models.Product;
import com.rideco.grocery.models.SaveProductRequest;
import com.rideco.grocery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Rest controller that handles http request for getting and saving Product data.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final SaveProductRequestToProductConverter productConverter;

    @Autowired
    public ProductsController(ProductsService productsService, SaveProductRequestToProductConverter productConverter) {
        this.productsService = productsService;
        this.productConverter = productConverter;
    }

    /**
     * Gets all products.
     *
     * @return the collection of all products.
     */
    @GetMapping
    public Iterable<Product> findAllProducts() {
        return this.productsService.findAll();
    }

    /**
     * Saves a new product.
     *
     * @param saveProductRequest   the product to save.
     * @param uriComponentsBuilder an instance of a builder for building URI.
     * @return an instance of the created Product.
     */
    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody SaveProductRequest saveProductRequest, UriComponentsBuilder uriComponentsBuilder) {
        Product savedProduct = this.productsService.save(this.productConverter.convert(saveProductRequest));

        URI location = uriComponentsBuilder
                .path("/products/")
                .path(savedProduct.getId().toString())
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
     * @param updateProductRequest the properties of the product to update.
     * @return an instance of the product updated.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @Valid @RequestBody SaveProductRequest updateProductRequest) {
        Product product = this.productsService.findById(productId);
        product.setName(updateProductRequest.getName());
        product.setDescription(updateProductRequest.getDescription());

        return this.productsService.save(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long productId) {
        return this.productsService.delete(productId);
    }
}
