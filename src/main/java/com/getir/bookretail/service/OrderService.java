package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.OrderRestRequest;
import com.getir.bookretail.controller.dto.request.OrdersByDateRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.OrderResponse;

/**
 * @author İkbal Arslan
 */
public interface OrderService {

    OrderResponse createNewOrder(OrderRestRequest request);

    CustomerOrdersResponse getAllOrdersBetweenDateInterval(OrdersByDateRestRequest request);

    OrderResponse getOrderById(String orderId);
}
