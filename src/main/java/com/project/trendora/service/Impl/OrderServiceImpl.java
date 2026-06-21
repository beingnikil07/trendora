package com.project.trendora.service.Impl;

import com.project.trendora.Repository.CartItemRepository;
import com.project.trendora.Repository.CartRepository;
import com.project.trendora.Repository.OrderRepository;
import com.project.trendora.Repository.UserRepository;
import com.project.trendora.dto.OrderItemResponse;
import com.project.trendora.dto.OrderRequest;
import com.project.trendora.dto.OrderResponse;
import com.project.trendora.dto.ViewOrderResponse;
import com.project.trendora.models.*;
import com.project.trendora.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private  final OrderRepository orderRepository;

    //To place order
    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //extract the user
        User user=userRepository.findById(orderRequest.getUserId()).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        //extract the cart of user
        Cart cart=cartRepository.findByUser(user);
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        //create an order object
        Order order=new Order();
        order.setUser(user);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setShippingAddress(orderRequest.getShippingAddress());
        //calculate the total amount
        double totalAmount=0;
        for (CartItems cartItem:cart.getCartItems()){
            totalAmount=totalAmount+cartItem.getProduct().getPrice() *cartItem.getQuantity();
        }
        order.setTotalAmount(totalAmount);

        //create order Items

        List<OrderItem> orderItems=new ArrayList<>();
        for (CartItems cartItem : cart.getCartItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());

            double price = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();

            orderItem.setPriceAtPurchase(price);
            orderItem.setQuantity(quantity);
            orderItem.setSubtotal(price * quantity);

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        //save the order
        Order savedOrder = orderRepository.save(order);
        //clear the cart
        cartItemRepository.deleteAll(cart.getCartItems());
        return mapToResponse(savedOrder);
    }

    //To get all the orders
    @Override
    public List<ViewOrderResponse> viewOrders(Long userId) {
        // check user
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // fetch orders
        List<Order> orders = orderRepository.findAllByUserUserId(userId);
        // convert Order -> ViewOrderResponse
        return orders.stream()
                .map(order -> {

                    ViewOrderResponse response = new ViewOrderResponse();

                    response.setOrderId(order.getOrderId());
                    response.setTotalAmount(order.getTotalAmount());
                    response.setStatus(order.getStatus());
                    response.setPaymentMethod(order.getPaymentMethod());
                    response.setPaymentStatus(order.getPaymentStatus());
                    response.setShippingAddress(order.getShippingAddress());
                    response.setOrderDate(order.getOrderDate());
                    response.setDeliveryDate(order.getDeliveryDate());

                    List<OrderItemResponse> items = order.getOrderItems()
                            .stream()
                            .map(item -> {

                                OrderItemResponse itemResponse = new OrderItemResponse();

                                itemResponse.setProductId(item.getProduct().getProductId());
                                itemResponse.setProductName(item.getProduct().getName());
                                itemResponse.setQuantity(item.getQuantity());
                                itemResponse.setPrice(item.getPriceAtPurchase());
                                itemResponse.setSubtotal(item.getSubtotal());

                                return itemResponse;

                            })
                            .toList();

                    response.setOrderItems(items);
                    return response;
                })
                .toList();
    }

    private OrderResponse mapToResponse(Order order){
        OrderResponse response=new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderStatus(order.getStatus());
        return response;
    }








}
