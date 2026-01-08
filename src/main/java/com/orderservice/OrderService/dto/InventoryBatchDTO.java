package com.orderservice.OrderService.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryBatchDTO {
    private Long batchId;
    private Integer quantity;
    private LocalDate expiryDate;
}
