package com.project.trendora.controller;

import com.project.trendora.dto.OrderRequest;
import com.project.trendora.dto.OrderResponse;
import com.project.trendora.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }
}
