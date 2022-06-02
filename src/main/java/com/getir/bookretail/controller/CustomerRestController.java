package com.getir.bookretail.controller;

import com.getir.bookretail.controller.dto.request.CustomerOrdersRestRequest;
import com.getir.bookretail.controller.dto.request.CustomerRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.model.Customer;
import com.getir.bookretail.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("retail/rest")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @PostMapping(value = "customer")
    public ResponseEntity<Customer> registerCustomer(@Validated @RequestBody CustomerRestRequest request) {

        log.info("Request to register new customer with email: {}", request.getEmail());
        final var result = customerService.registerCustomer(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "customer-orders")
    public ResponseEntity<CustomerOrdersResponse> getAllOrdersOfTheCustomer(@Validated @RequestBody CustomerOrdersRestRequest request) {

        log.info("Request to get all orders of the customer with id : {} ", request.getCustomerId());
        final var customerId = request.getCustomerId();
        final var paging = PageRequest.of(request.getPage(), request.getSize());
        final var result = customerService.getAllOrdersOfTheCustomer(customerId, paging);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
