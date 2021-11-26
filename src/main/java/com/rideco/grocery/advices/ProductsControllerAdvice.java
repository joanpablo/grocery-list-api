package com.rideco.grocery.advices;

import com.rideco.grocery.exceptions.ProductNotFoundException;
import com.rideco.grocery.models.ErrorResponse;
import com.rideco.grocery.models.ErrorResponseCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductsControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(final ProductNotFoundException error) {
        return new ErrorResponse(error.getMessage(), ErrorResponseCode.VALIDATION_ERROR);
    }
}
