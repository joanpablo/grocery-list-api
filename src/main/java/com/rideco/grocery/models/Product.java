package com.rideco.grocery.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Represents a Product of a grocery list.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "The name can not be null")
    @NotEmpty(message = "The name can not be empty")
    private String name;

    private String description;

    /**
     * Sets the ID of the product.
     * @param id the identifier of the product.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the product.
     * @return the id of the product.
     */
    public Long getId() {
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

    /**
     * Sets the name of the product.
     * @param name the new name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the product.
     * @param description the new description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
