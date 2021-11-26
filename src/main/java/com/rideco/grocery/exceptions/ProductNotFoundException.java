package com.rideco.grocery.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {
        super("Product with id " + productId.toString() + " not found");
    }
}
