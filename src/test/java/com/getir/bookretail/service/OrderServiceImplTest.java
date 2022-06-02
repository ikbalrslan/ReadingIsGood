package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.OrderRestRequest;
import com.getir.bookretail.controller.dto.request.OrdersByDateRestRequest;
import com.getir.bookretail.enums.OrderStatus;
import com.getir.bookretail.model.*;
import com.getir.bookretail.repository.BookRepository;
import com.getir.bookretail.repository.CustomerRepository;
import com.getir.bookretail.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author İkbal Arslan
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldCreateNewOrderSuccessfully() {
        when(orderRepository.getOrdersByCustomerIdAndBookId(anyString(), anyString())).thenReturn(Optional.empty());
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(buildBook()));
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(buildCustomer()));
        when(orderRepository.insert(buildOrder())).thenReturn(buildOrder());

        final var actual = orderService.createNewOrder(createRequest());

        assertEquals("1", actual.getCustomerId());
        assertEquals("1", actual.getBookId());
        assertEquals(5, actual.getCount());
        assertEquals(2, actual.getStatus().getIntValue());

        verify(orderRepository, times(1)).getOrdersByCustomerIdAndBookId(anyString(), anyString());
    }

    @Test
    void shouldGetAllOrdersBetweenDateInterval() {
        when(orderRepository.getOrdersByDate(any(), any())).thenReturn(List.of(buildOrder()));
        final var actual = orderService.getAllOrdersBetweenDateInterval(createOrdersByDateRequest());

        assertEquals("1", actual.getOrders().get(0).getCustomerId());
        assertEquals("1", actual.getOrders().get(0).getBookId());
        assertEquals(5, actual.getOrders().get(0).getCount());
        assertEquals(2, actual.getOrders().get(0).getStatus().getIntValue());
    }

    @Test
    void shouldGetOrderBySuccessfully() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(buildOrder()));
        final var actual = orderService.getOrderById("1");

        assertEquals("1", actual.getCustomerId());
        assertEquals("1", actual.getBookId());
        assertEquals(5, actual.getCount());
        assertEquals(2, actual.getStatus().getIntValue());
    }

    private Order buildOrder() {
        return Order.builder()
                .customerId("1")
                .bookId("1")
                .count(5)
                .status(OrderStatus.fromIntValue(2))
                .orderTime(LocalDateTime.now().withNano(0))
                .build();
    }

    private Book buildBook() {
        return Book.builder()
                .bookName("Açlık")
                .totalPage(250)
                .author("Knut Hamsun")
                .genre("dram")
                .stock(50)
                .price(34.33)
                .build();
    }

    private Customer buildCustomer() {
        return Customer.builder()
                .firstName("Enes")
                .lastName("Koçak")
                .gender(Gender.MALE)
                .address(buildAdress())
                .email("ekones@gmail.com")
                .statistics(new HashMap<>())
                .accountCreateTime(dtf.format(LocalDateTime.now()))
                .build();
    }

    private Address buildAdress() {
        return Address.builder()
                .city("Istanbul")
                .country("Turkey")
                .postCode("34410")
                .build();
    }

    private OrderRestRequest createRequest() {
        final var request = new OrderRestRequest();
        request.setCustomerId("1");
        request.setBookId("1");
        request.setCount(5);
        request.setStatus(2);
        return request;
    }

    private OrdersByDateRestRequest createOrdersByDateRequest() {
        final var request = new OrdersByDateRestRequest();
        request.setStartDate("2022/05/12 01:00:00");
        request.setEndDate("2022/06/03 03:50:39");
        return request;
    }
}