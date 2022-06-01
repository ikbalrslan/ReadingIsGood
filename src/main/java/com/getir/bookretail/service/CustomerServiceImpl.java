package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.CustomerRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.StatisticsResponse;
import com.getir.bookretail.fault.ResourceAlreadyExistException;
import com.getir.bookretail.fault.ResourceNotFoundException;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Customer;
import com.getir.bookretail.model.Order;
import com.getir.bookretail.model.Statistic;
import com.getir.bookretail.repository.CustomerRepository;
import com.getir.bookretail.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author İkbal Arslan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Override
    @Transactional
    public Customer registerCustomer(CustomerRestRequest request) {
        final var customer = customerRepository.getCustomerByEmail(request.getEmail());
        if (customer.isEmpty()) {
            final var customerInstance = buildCustomer(request);
            return customerRepository.insert(customerInstance);
        } else {
            throw new ResourceAlreadyExistException("Customer already exist with email : " + customer.get().getEmail());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerOrdersResponse getAllOrdersOfTheCustomer(String customerId, PageRequest paging) {
        final var pageOrders = orderRepository.getOrdersByCustomerId(customerId, paging);
        final var orders = pageOrders.getContent();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found for customerId : " + customerId);
        } else {
            return convertToResponse(orders);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public StatisticsResponse getStatisticsOfTheCustomer(String customerId) {
        final var customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer not exist with customerId :" + customerId));

        return convertToStatistcsResponse(customer);
    }

    private CustomerOrdersResponse convertToResponse(List<Order> orders) {
        final var response = new CustomerOrdersResponse();
        response.setOrders(orders);
        return response;
    }

    private StatisticsResponse convertToStatistcsResponse(Customer customer) {
        final var response = new StatisticsResponse();
        response.setStatistics(buildStatistics(customer));
        return response;
    }

    private List<Statistic> buildStatistics(Customer customer) {
        final var statistics = new ArrayList<Statistic>();
        customer.getStatistics().forEach((key, value) -> statistics.add(value));
        return statistics;
    }

    private Customer buildCustomer(CustomerRestRequest request) {
        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .gender(request.getGender())
                .address(request.getAddress())
                .statistics(new HashMap<>())
                .accountCreateTime(dtf.format(LocalDateTime.now()))
                .build();
    }

    @Override
    public void updateMonthlyStatistics(Customer customer, String month, int bookCount, Book book){
        final var monthlyStatistics = customer.getStatistics().get(month);
        final var monthlyOrderCount = monthlyStatistics.getTotalOrderCount();
        final var monthlyBookCount = monthlyStatistics.getTotalBookCount();
        final var monthlyTPA = monthlyStatistics.getTotalPurchasedAmount();
        monthlyStatistics.setMonth(month);
        monthlyStatistics.setTotalOrderCount(monthlyOrderCount + 1);
        monthlyStatistics.setTotalBookCount(monthlyBookCount + bookCount);
        monthlyStatistics.setTotalPurchasedAmount(monthlyTPA + book.getPrice());
        customer.getStatistics().put(month, monthlyStatistics);
        customerRepository.save(customer);
    }
}
