package com.project.trendora.controller;

import com.project.trendora.dto.ProductRequest;
import com.project.trendora.dto.ProductResponse;
import com.project.trendora.service.Impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductInfo(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/get/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<ProductResponse> updateProduct(@RequestParam Long id,@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.updateProduct(id,productRequest));
    }

}
