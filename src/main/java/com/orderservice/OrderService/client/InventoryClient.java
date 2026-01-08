package com.orderservice.OrderService.client;

import com.orderservice.OrderService.dto.ProductInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {
    @Autowired
    private RestTemplate restTemplate;

    public ProductInventoryResponse getInventory(Long productId) {
        return restTemplate.getForObject(
            "http://localhost:8081/inventory/" + productId,
                ProductInventoryResponse.class
        );
    }

    public void updateInventory(Long productId, Integer quantity) {
        restTemplate.postForObject(
                "http://localhost:8081/inventory/update?productId=" + productId + "&quantity=" + quantity,
                null,String.class
        );
    }
}
