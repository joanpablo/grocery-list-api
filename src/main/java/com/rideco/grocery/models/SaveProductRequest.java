package com.rideco.grocery.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Represents an input for create or update a product.
 */
public class SaveProductRequest {
    @NotNull(message = "The name can not be null")
    @NotEmpty(message = "The name can not be empty")
    private String name;
    private String description;

    /**
     * Gets the name of the product.
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the product.
     * @return the description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the name of the product to create.
     * @param name the name of product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the product to create.
     * @param description the description of product.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
