package com.getir.bookretail.controller;

import com.getir.bookretail.controller.dto.request.CustomerOrdersRestRequest;
import com.getir.bookretail.controller.dto.request.CustomerRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.StatisticsResponse;
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
public class StatisticsRestController {
    private final CustomerService customerService;

    @GetMapping(value = "statistics")
    public ResponseEntity<StatisticsResponse> getAllOrdersOfTheCustomer(@Validated @RequestBody CustomerOrdersRestRequest request) {

        final var customerId = request.getCustomerId();
        log.info("Request to get customer statistics with customerId : {} ", customerId);
        final var result = customerService.getStatisticsOfTheCustomer(customerId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
