package com.project.trendora.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class ProductResponse {

    private Long productId;
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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
