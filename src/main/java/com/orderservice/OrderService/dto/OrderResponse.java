package com.orderservice.OrderService.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private String status;
    private List<Long> reservedFromBathIds;
    private String message;

}
