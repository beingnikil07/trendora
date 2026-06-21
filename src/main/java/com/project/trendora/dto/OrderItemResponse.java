package com.project.trendora.dto;

import lombok.Data;

@Data
public class OrderItemResponse {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double price;
        private Double subtotal;
}
