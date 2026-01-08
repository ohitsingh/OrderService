package com.orderservice.OrderService.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductInventoryResponse {
    private Long productId;
    private String productName;
    private List<InventoryBatchDTO> batches;
}
