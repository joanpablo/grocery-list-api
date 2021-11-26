package com.rideco.grocery.repositories;

import com.rideco.grocery.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This represents a repository for accessing and saving Product data in database.
 */
public interface ProductsRepository extends MongoRepository<Product, Integer> {
}
