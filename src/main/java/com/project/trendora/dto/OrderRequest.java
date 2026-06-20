package com.project.trendora.dto;

import com.project.trendora.enums.PaymentMethod;
import lombok.Data;

@Data
public class OrderRequest {

    private Long userId;
    private PaymentMethod paymentMethod;
    private String shippingAddress;

}
