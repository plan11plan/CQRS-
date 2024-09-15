package com.example.cqrs_basic.application.query.dto;

import java.util.List;
import lombok.Data;

@Data
public class CustomerOrdersDto {
    private String customerName;
    private String customerEmail;
    private List<OrderSummaryDto> orders;
}