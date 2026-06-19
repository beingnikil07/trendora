package com.project.trendora.service.Impl;

import com.project.trendora.Repository.ProductRepository;
import com.project.trendora.dto.ProductRequest;
import com.project.trendora.dto.ProductResponse;
import com.project.trendora.exceptions.ProductAlreadyExist;
import com.project.trendora.exceptions.ProductNotFoundException;
import com.project.trendora.models.Product;
import com.project.trendora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        //check product already exist or not
        if(productRepository.existsByName(productRequest.getName())){
            throw new ProductAlreadyExist("Product already exists");
        }

        Product product=new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setBrand(productRequest.getBrand());
        product.setAvailable(productRequest.getAvailable());
        product.setAverageRating(productRequest.getAverageRating());
        product.setImageUrl(productRequest.getImageUrl());
        product.setTotalReviews(productRequest.getTotalReviews());
        product.setStockQuantity(productRequest.getStockQuantity());

       Product savedProduct= productRepository.save(product);
       return mapToResponse(savedProduct);
    }

    //Get a product info
    @Override
    public ProductResponse getProduct(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product not found"));
        return mapToResponse(product);
    }
    //get all products
    @Override
    public List<ProductResponse> getAllProducts(){
//        List<Product> products=productRepository.findAll();
//        List<ProductResponse> responses=new ArrayList<>();
//
//        for(Product product:products){
//            ProductResponse productResponse=mapToResponse(product);
//            responses.add(productResponse);
//        }
//        return responses;

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //delete a product
    @Override
    public String deleteProduct(Long id){
     if(!productRepository.existsById(id)){
         return "Product not found";
     }
     productRepository.deleteById(id);
     return "User deleted successfully";
    }

    //to update the product
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        //fetch the product from DB which i want to update only
        Product product=productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product not found"));

        if (productRequest.getName()!=null){
            product.setName(productRequest.getName());
        }

        if (productRequest.getDescription()!=null){
            product.setDescription(productRequest.getDescription());
        }
        if (productRequest.getPrice()!=null){
            product.setPrice(productRequest.getPrice());
        }

        if (productRequest.getBrand()!=null){
            product.setBrand(productRequest.getBrand());
        }
        if(productRequest.getAvailable()!=null){
            product.setAvailable(productRequest.getAvailable());
        }
        if (productRequest.getAverageRating()!=null){
            product.setAverageRating(productRequest.getAverageRating());
        }
        if (productRequest.getImageUrl()!=null){
            product.setImageUrl(productRequest.getImageUrl());
        }
        if (productRequest.getTotalReviews()!=null){
            product.setTotalReviews(productRequest.getTotalReviews());
        }
        if (productRequest.getStockQuantity()!=null){
            product.setStockQuantity(productRequest.getStockQuantity());
        }

        Product savedProduct= productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    private  ProductResponse mapToResponse(Product savedProduct){
        ProductResponse response=new ProductResponse();
        response.setProductId(savedProduct.getProductId());
        response.setBrand(savedProduct.getBrand());
        response.setDescription(savedProduct.getDescription());
        response.setAvailable(savedProduct.getAvailable());
        response.setName(savedProduct.getName());
        response.setAverageRating(savedProduct.getAverageRating());
        response.setPrice(savedProduct.getPrice());
        response.setCreatedAt(savedProduct.getCreatedAt());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setTotalReviews(savedProduct.getTotalReviews());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setUpdatedAt(savedProduct.getUpdatedAt());
        return response;
    }

}
