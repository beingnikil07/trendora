package com.project.trendora.service;

import com.project.trendora.dto.AddToCartRequest;
import com.project.trendora.dto.RemoveFromCartRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {

     String addToCart(AddToCartRequest addToCartRequest);
     String removeFromCart(RemoveFromCartRequest request);
}
