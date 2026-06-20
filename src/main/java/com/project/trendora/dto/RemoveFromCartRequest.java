package com.project.trendora.dto;

import lombok.Data;

@Data
public class RemoveFromCartRequest {
    private Long userId;
    private Long productId;
    private Integer quantity;

}
