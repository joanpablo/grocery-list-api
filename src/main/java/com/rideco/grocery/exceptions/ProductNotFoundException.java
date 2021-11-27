package com.rideco.grocery.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super(String.format("Product with Id %d not found", productId));
    }
}
