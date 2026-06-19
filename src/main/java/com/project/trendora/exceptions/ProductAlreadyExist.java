package com.project.trendora.exceptions;

public class ProductAlreadyExist extends RuntimeException {
    public ProductAlreadyExist(String message) {
        super(message);
    }
}
