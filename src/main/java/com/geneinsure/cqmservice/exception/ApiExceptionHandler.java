package com.geneinsure.cqmservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResp> handleValidationExceptions(MethodArgumentNotValidException mave) {
        log.error("Request Object Validation Exception occurred - {}", mave.getMessage(), mave);

        Map<String, String> errors = new HashMap<>();
        mave.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mave.getMessage() + errors);
        return ResponseEntity.badRequest().body(apiErrorResp);
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResp> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
        log.error("ResourceNotFound Exception occurred - {}", rnfe.getMessage(), rnfe);
        ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResp);
    }

}
