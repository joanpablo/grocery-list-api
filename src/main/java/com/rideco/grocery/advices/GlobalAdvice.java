package com.rideco.grocery.advices;

import com.rideco.grocery.exceptions.ProductNotFoundException;
import com.rideco.grocery.models.ErrorResponse;
import com.rideco.grocery.models.ErrorResponseCode;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Order
public class GlobalAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGenericException(final Exception error) throws Exception {

        if (AnnotationUtils.findAnnotation(error.getClass(), ResponseStatus.class) != null || error instanceof ResponseStatusException) {
            throw error;
        }

        return new ErrorResponse()
                .setErrorMessage(error.getMessage())
                .setErrorCode(ErrorResponseCode.INTERNAL_SERVER_ERROR);
    }
}
