package com.getir.bookretail.controller;

import com.getir.bookretail.controller.dto.request.OrderByIdRestRequest;
import com.getir.bookretail.controller.dto.request.OrderRestRequest;
import com.getir.bookretail.controller.dto.request.OrdersByDateRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.OrderResponse;
import com.getir.bookretail.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("retail/rest")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping(value = "order")
    public ResponseEntity<OrderResponse> createNewOrder(@Validated @RequestBody OrderRestRequest request) {

        log.info("Request to create a new order with customerId : {} and bookId : {}", request.getCustomerId(), request.getBookId());
        final var result = orderService.createNewOrder(request);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "orders")
    public ResponseEntity<CustomerOrdersResponse> getAllOrdersByDateInterval(@Validated @RequestBody OrdersByDateRestRequest request) {

        log.info("Request to orders between startDate : {} and endDate : {}", request.getStartDate(), request.getEndDate());
        final var result = orderService.getAllOrdersBetweenDateInterval(request);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "order")
    public ResponseEntity<OrderResponse> getOrderById(@Validated @RequestBody OrderByIdRestRequest request) {

        final var orderId = request.getOrderId();
        log.info("Request to order by orderId: {}", orderId);
        final var result = orderService.getOrderById(orderId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
