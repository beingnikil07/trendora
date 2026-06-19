package com.project.trendora.controller;

import com.project.trendora.dto.AddToCartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest addToCartRequest){
        return ResponseEntity.ok("Item added successfully");
    }

}
