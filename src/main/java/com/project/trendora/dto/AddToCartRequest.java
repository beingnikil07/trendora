package com.project.trendora.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
