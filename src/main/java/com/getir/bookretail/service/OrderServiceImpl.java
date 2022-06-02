package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.OrderRestRequest;
import com.getir.bookretail.controller.dto.request.OrdersByDateRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.OrderResponse;
import com.getir.bookretail.enums.OrderStatus;
import com.getir.bookretail.fault.NotEnoughResourcesException;
import com.getir.bookretail.fault.ResourceNotFoundException;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Order;
import com.getir.bookretail.model.Statistic;
import com.getir.bookretail.repository.BookRepository;
import com.getir.bookretail.repository.CustomerRepository;
import com.getir.bookretail.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author İkbal Arslan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Override
    @Transactional
    public OrderResponse createNewOrder(OrderRestRequest request) {
        final var order = orderRepository.getOrdersByCustomerIdAndBookId(request.getCustomerId(), request.getBookId());
        updateBookStock(request.getBookId(), request.getCount());
        if (order.isEmpty()) {
            final var orderInstance = buildOrder(request);

            updateCustomerStatistics(request.getCustomerId(), request.getCount(), request.getBookId());
            return convertToOrderResponse(orderRepository.insert(orderInstance));
        } else { // In different order, if purchase is repeated with the same book, add itemCount to previous order
            final var itemCount = order.get().getCount();
            order.get().setCount(itemCount + request.getCount());

            updateCustomerStatistics(request.getCustomerId(), request.getCount(), request.getBookId());
            return convertToOrderResponse(orderRepository.save(order.get()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerOrdersResponse getAllOrdersBetweenDateInterval(OrdersByDateRestRequest request) {
        final var startTime = LocalDateTime.parse(request.getStartDate(), dtf);
        final var endTime = LocalDateTime.parse(request.getEndDate(), dtf);
        final var orders = orderRepository.getOrdersByDate(startTime, endTime);

        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders were found within the entered date range");
        } else {
            return convertToResponse(orders);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(String orderId) {
        final var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for orderId : " + orderId));
        return convertToOrderResponse(order);
    }

    private CustomerOrdersResponse convertToResponse(List<Order> orders) {
        final var response = new CustomerOrdersResponse();
        response.setOrders(orders);
        return response;
    }

    private OrderResponse convertToOrderResponse(Order order) {
        final var response = new OrderResponse();
        response.setCustomerId(order.getCustomerId());
        response.setBookId(order.getBookId());
        response.setCount(order.getCount());
        response.setStatus(order.getStatus());
        response.setOrderTime(dtf.format(order.getOrderTime()));
        return response;
    }

    private void updateBookStock(String bookId, int orderCount) {
        final var bookInfo = bookRepository.findById(bookId);

        if (bookInfo.isPresent()) {
            final var stock = bookInfo.get().getStock() - orderCount;
            if (stock < 0) {
                throw new NotEnoughResourcesException("Book stock with bookId: " + bookId + " not enough for order process");
            } else {
                bookInfo.get().setStock(stock);
                bookRepository.save(bookInfo.get());
                log.info("Stock number: {} set to book with bookId: {}", stock, bookId);
            }
        } else {
            throw new ResourceNotFoundException("Book not found for bookId : " + bookId);
        }
    }

    private void updateCustomerStatistics(String customerId, int bookCount, String bookId) {
        final var book = bookRepository.findById(bookId).get();
        final var customer = customerRepository.findById(customerId).get();
        final var month = LocalDateTime.now().getMonth().toString();
        final var customerStatistics = customer.getStatistics();
        if (customerStatistics.keySet().contains(month)) {
            customerService.updateMonthlyStatistics(customer, month, bookCount, book);
        } else {
            customer.getStatistics().put(month, buildStatistics(bookCount, book));
            customerRepository.save(customer);
        }
    }

    private Order buildOrder(OrderRestRequest request) {
        return Order.builder()
                .customerId(request.getCustomerId())
                .bookId(request.getBookId())
                .count(request.getCount())
                .status(OrderStatus.fromIntValue(request.getStatus()))
                .orderTime(LocalDateTime.now())
                .build();
    }

    private Statistic buildStatistics(int bookCount, Book book) {
        return Statistic.builder()
                .totalOrderCount(1)
                .totalBookCount(bookCount)
                .totalPurchasedAmount(book.getPrice())
                .month(LocalDateTime.now().getMonth().toString())
                .build();
    }
}
