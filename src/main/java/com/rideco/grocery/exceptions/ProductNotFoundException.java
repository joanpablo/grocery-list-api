package com.rideco.grocery.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {
        super(String.format("Product with Id %d not found", productId));
    }
}
