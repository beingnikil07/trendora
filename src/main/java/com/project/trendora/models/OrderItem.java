package com.project.trendora.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which order this item belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Which product is being purchased
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Number of units purchased
    @Column(nullable = false)
    private Integer quantity;

    // Product price at the time of purchase
    @Column(nullable = false)
    private Double priceAtPurchase;

    // Total price for this item
    @Column(nullable = false)
    private Double subtotal;
}