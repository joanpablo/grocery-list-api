package com.rideco.grocery.converters;

import com.rideco.grocery.models.Product;
import com.rideco.grocery.models.SaveProductRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SaveProductRequestToProductConverter implements Converter<SaveProductRequest, Product> {
    @Override
    public Product convert(SaveProductRequest source) {
        Product product = new Product();
        product.setName(source.getName());
        product.setDescription(source.getDescription());

        return product;
    }
}
