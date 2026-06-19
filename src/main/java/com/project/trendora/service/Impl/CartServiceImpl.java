package com.project.trendora.service.Impl;

import com.project.trendora.Repository.CartItemRepository;
import com.project.trendora.Repository.CartRepository;
import com.project.trendora.Repository.ProductRepository;
import com.project.trendora.Repository.UserRepository;
import com.project.trendora.dto.AddToCartRequest;
import com.project.trendora.models.Cart;
import com.project.trendora.models.CartItems;
import com.project.trendora.models.Product;
import com.project.trendora.models.User;
import com.project.trendora.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public String addToCart(AddToCartRequest addToCartRequest) {

        // find the user
        User user = userRepository.findById(addToCartRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // find the product
        Product product = productRepository.findById(addToCartRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // get or create cart
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        // check if product already exists in cart
        Optional<CartItems> existingItem =
                cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {

            CartItems item = existingItem.get();

            item.setQuantity(
                    item.getQuantity() + addToCartRequest.getQuantity()
            );

            item.setSubtotal(
                    item.getQuantity() * product.getPrice()
            );

            cartItemRepository.save(item);

        } else {

            CartItems item = new CartItems();

            item.setCart(cart);

            item.setProduct(product);

            item.setQuantity(addToCartRequest.getQuantity());

            item.setSubtotal(
                    addToCartRequest.getQuantity() * product.getPrice()
            );

            cartItemRepository.save(item);
        }

        return "Item added to cart successfully";
    }
}
