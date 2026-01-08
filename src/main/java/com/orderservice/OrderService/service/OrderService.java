package com.orderservice.OrderService.service;

import com.orderservice.OrderService.dto.OrderRequest;
import com.orderservice.OrderService.dto.OrderResponse;
import org.springframework.stereotype.Service;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);
}
