package com.orderservice.OrderService.service;

import com.orderservice.OrderService.client.InventoryClient;
import com.orderservice.OrderService.dto.InventoryBatchDTO;
import com.orderservice.OrderService.dto.OrderRequest;
import com.orderservice.OrderService.dto.OrderResponse;
import com.orderservice.OrderService.dto.ProductInventoryResponse;
import com.orderservice.OrderService.entity.Order;
import com.orderservice.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        ProductInventoryResponse inventory = inventoryClient.getInventory(request.getProductId());

        int remaining = request.getQuantity();

        List<Long> usedBatches = new ArrayList<>();

        for(InventoryBatchDTO batch : inventory.getBatches()) {
            if (remaining < 0) break;
            int available = batch.getQuantity();
            if (available>0){
                usedBatches.add(batch.getBatchId());
                remaining -= available;
            }
        }

        // update inventory
        inventoryClient.updateInventory(request.getProductId(), request.getQuantity());

        // save order
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setProductName(inventory.getProductName());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());
        orderRepository.save(order);

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .productId(order.getProductId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .reservedFromBathIds(usedBatches)
                .message("Order placed successfully. Inventory reserved.")
                .build();
    }
}
