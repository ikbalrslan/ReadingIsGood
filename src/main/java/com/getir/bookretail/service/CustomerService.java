package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.CustomerRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.StatisticsResponse;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Customer;
import org.springframework.data.domain.PageRequest;

/**
 * @author İkbal Arslan
 */
public interface CustomerService {

    Customer registerCustomer(CustomerRestRequest request);

    CustomerOrdersResponse getAllOrdersOfTheCustomer(String customerId, PageRequest paging);

    void updateMonthlyStatistics(Customer customer, String month, int bookCount, Book book);

    StatisticsResponse getStatisticsOfTheCustomer(String customerId);
}
