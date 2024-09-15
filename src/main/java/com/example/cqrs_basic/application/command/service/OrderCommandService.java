package com.example.cqrs_basic.application.command.service;

import com.example.cqrs_basic.application.command.dto.CreateOrderCommand;
import com.example.cqrs_basic.application.common.OrderStatus;
import com.example.cqrs_basic.domain.model.Customer;
import com.example.cqrs_basic.domain.model.Order;
import com.example.cqrs_basic.domain.model.OrderItem;
import com.example.cqrs_basic.domain.model.Product;
import com.example.cqrs_basic.domain.repository.CustomerRepository;
import com.example.cqrs_basic.domain.repository.OrderRepository;
import com.example.cqrs_basic.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderCommandService(OrderRepository orderRepository,
                               CustomerRepository customerRepository,
                               ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Long createOrder(CreateOrderCommand command) {
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        for (CreateOrderCommand.OrderItemDto itemDto : command.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());
            order.addOrderItem(orderItem);
        }

        order.calculateTotalAmount();
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }
}