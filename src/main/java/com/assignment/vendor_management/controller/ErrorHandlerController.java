package com.assignment.vendor_management.controller;

import com.assignment.vendor_management.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorHandlerController {
   
   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<WebResponse<String>> constraintValidationHandler(ConstraintViolationException e) {
      return ResponseEntity.badRequest().body(WebResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).error(e.getMessage()).build());
   }
   
   @ExceptionHandler(ResponseStatusException.class)
   public ResponseEntity<WebResponse<String>> responseStatusExceptionHandler(ResponseStatusException e) {
      return ResponseEntity.status(e.getStatusCode()).body(WebResponse.<String>builder().status(e.getStatusCode().value()).error(e.getReason()).build());
   }
}
