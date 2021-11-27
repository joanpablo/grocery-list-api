package com.rideco.grocery.advices;

import com.rideco.grocery.exceptions.ProductNotFoundException;
import com.rideco.grocery.models.ErrorResponse;
import com.rideco.grocery.models.ErrorResponseCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Represents an Advice that handles common errors that may be thrown by the ProductsController.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductsControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(final ProductNotFoundException error) {
        return new ErrorResponse()
                .setErrorMessage(error.getMessage())
                .setErrorCode(ErrorResponseCode.VALIDATION_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidationException(final MethodArgumentNotValidException error) {
        String message = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
        return new ErrorResponse()
                .setErrorMessage(message)
                .setErrorCode(ErrorResponseCode.VALIDATION_ERROR);
    }
}
