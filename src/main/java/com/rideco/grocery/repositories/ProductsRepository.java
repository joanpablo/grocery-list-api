package com.rideco.grocery.repositories;

import com.rideco.grocery.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * This represents a repository for accessing and saving Product data in database.
 */
public interface ProductsRepository extends CrudRepository<Product, Integer> {
    Iterable<Product> findAllByOrderByIdDesc();
}
