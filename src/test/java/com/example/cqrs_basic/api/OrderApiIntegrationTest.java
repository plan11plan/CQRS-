package com.example.cqrs_basic.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.cqrs_basic.application.command.dto.CreateOrderCommand;
import com.example.cqrs_basic.application.query.dto.CustomerOrdersDto;
import com.example.cqrs_basic.application.query.dto.OrderSummaryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateOrderAndRetrieve() throws Exception {
        // 1. Create an order
        CreateOrderCommand createOrderCommand = new CreateOrderCommand();
        createOrderCommand.setCustomerId(1L);
        createOrderCommand.setItems(Arrays.asList(
                new CreateOrderCommand.OrderItemDto(1L, 2),
                new CreateOrderCommand.OrderItemDto(2L, 1)
        ));

        MvcResult createResult = mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderCommand)))
                .andExpect(status().isOk())
                .andReturn();

        String content = createResult.getResponse().getContentAsString();
        Long orderId = Long.parseLong(content);

        // 2. Retrieve order summary
        MvcResult summaryResult = mockMvc.perform(get("/api/orders/" + orderId))
                .andExpect(status().isOk())
                .andReturn();

        OrderSummaryDto orderSummary = objectMapper.readValue(summaryResult.getResponse().getContentAsString(), OrderSummaryDto.class);
        assertEquals("John Doe", orderSummary.getCustomerName());
        assertEquals(175.50, orderSummary.getTotalAmount().doubleValue(), 0.01);
        assertEquals(3, orderSummary.getItemCount());

        // 3. Retrieve customer orders
        MvcResult customerOrdersResult = mockMvc.perform(get("/api/orders/customer/1"))
                .andExpect(status().isOk())
                .andReturn();

        CustomerOrdersDto customerOrders = objectMapper.readValue(customerOrdersResult.getResponse().getContentAsString(), CustomerOrdersDto.class);
        assertEquals("John Doe", customerOrders.getCustomerName());
        assertTrue(customerOrders.getOrders().size() >= 1);
        assertTrue(customerOrders.getOrders().stream().anyMatch(order -> order.getId().equals(orderId)));
    }
}