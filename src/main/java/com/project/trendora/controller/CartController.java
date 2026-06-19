package com.project.trendora.controller;

import com.project.trendora.dto.AddToCartRequest;
import com.project.trendora.service.Impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest addToCartRequest){
        return ResponseEntity.ok(cartService.addToCart(addToCartRequest));
    }

}
