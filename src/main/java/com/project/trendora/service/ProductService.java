package com.project.trendora.service;

import com.project.trendora.dto.ProductRequest;
import com.project.trendora.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse getProduct(Long id);
    List<ProductResponse> getAllProducts();
    String deleteProduct(Long id);
    ProductResponse updateProduct(Long id,ProductRequest productRequest);
}
