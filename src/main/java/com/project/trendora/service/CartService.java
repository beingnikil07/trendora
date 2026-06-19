package com.project.trendora.service;

import com.project.trendora.dto.AddToCartRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {

     String addToCart(AddToCartRequest addToCartRequest);
}
