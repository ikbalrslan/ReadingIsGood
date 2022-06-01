package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.BookRestRequest;
import com.getir.bookretail.controller.dto.request.OrderRestRequest;
import com.getir.bookretail.controller.dto.request.OrdersByDateRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.OrderResponse;
import com.getir.bookretail.controller.dto.response.UpdateBookResponse;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Order;

import java.util.List;

/**
 * @author İkbal Arslan
 */
public interface OrderService {

    OrderResponse createNewOrder(OrderRestRequest request);

    CustomerOrdersResponse getAllOrdersBetweenDateInterval(OrdersByDateRestRequest request);

    OrderResponse getOrderById(String orderId);
}
