package com.project.trendora.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    @Column(length = 220)
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String brand;
    private String imageUrl;
    private Boolean available;
    private Double averageRating;
    private Integer totalReviews;
}
