package com.rideco.grocery.models;

import org.springframework.data.annotation.Id;

/**
 * Represents a Product.
 */
public class Product {
    @Id
    private Integer id;
    private String name;
    private String description;

    public Product() {}

    /**
     * Sets the ID of the product.
     * @param id the identifier of the product.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the ID of the product.
     * @return the id of the product.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name of the product.
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the product.
     * @return the description of the product or null.
     */
    public String getDescription() {
        return description;
    }
}
