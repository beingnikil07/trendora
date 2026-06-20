package com.project.trendora.service;

import com.project.trendora.dto.OrderRequest;
import com.project.trendora.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);

}
