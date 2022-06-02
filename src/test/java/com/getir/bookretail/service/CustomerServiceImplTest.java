package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.CustomerRestRequest;
import com.getir.bookretail.enums.OrderStatus;
import com.getir.bookretail.model.*;
import com.getir.bookretail.repository.CustomerRepository;
import com.getir.bookretail.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
class CustomerServiceImplTest {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldRegisterCustomerSuccessfully() {
        when(customerRepository.getCustomerByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.insert(buildCustomer())).thenReturn(buildCustomer());

        final var actual = customerService.registerCustomer(createRequest());
        assertEquals("ekones@gmail.com", actual.getEmail());
        assertEquals("Enes", actual.getFirstName());
        assertEquals("Koçak", actual.getLastName());

        verify(customerRepository, times(1)).getCustomerByEmail(anyString());
    }

    @Test
    void getAllOrdersOfTheCustomer() {
        final var pageable = PageRequest.of(0, 3);
        final var pageableOrders = new PageImpl<>(List.of(buildOrder()), pageable, List.of(buildOrder()).size());
        when(orderRepository.getOrdersByCustomerId(anyString(), any())).thenReturn(pageableOrders);

        final var actual = customerService.getAllOrdersOfTheCustomer(anyString(), any());
        assertEquals("1", actual.getOrders().get(0).getCustomerId());
        assertEquals("1", actual.getOrders().get(0).getBookId());
        assertEquals(5, actual.getOrders().get(0).getCount());

        verify(orderRepository, times(1)).getOrdersByCustomerId(anyString(), any());
    }

    @Test
    void getStatisticsOfTheCustomer() {
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(buildCustomerWithStatistics()));

        final var actual = customerService.getStatisticsOfTheCustomer("1");

        assertEquals("JUNE", actual.getStatistics().get(0).getMonth());
        assertEquals(5, actual.getStatistics().get(0).getTotalBookCount());
        assertEquals(2, actual.getStatistics().get(0).getTotalOrderCount());
        assertEquals(52., actual.getStatistics().get(0).getTotalPurchasedAmount());

        verify(customerRepository, times(1)).findById(anyString());
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

    private Customer buildCustomerWithStatistics() {
        return Customer.builder()
                .firstName("Enes")
                .lastName("Koçak")
                .gender(Gender.MALE)
                .address(buildAdress())
                .email("ekones@gmail.com")
                .statistics(buildStatistic())
                .accountCreateTime(dtf.format(LocalDateTime.now()))
                .build();
    }

    private CustomerRestRequest createRequest() {
        final var request = new CustomerRestRequest();
        request.setFirstName("Enes");
        request.setLastName("Koçak");
        request.setGender(Gender.MALE);
        request.setAddress(buildAdress());
        request.setEmail("ekones@gmail.com");
        return request;
    }

    private Address buildAdress() {
        return Address.builder()
                .city("Istanbul")
                .country("Turkey")
                .postCode("34410")
                .build();
    }

    private Order buildOrder() {
        return Order.builder()
                .customerId("1")
                .bookId("1")
                .count(5)
                .status(OrderStatus.fromIntValue(2))
                .orderTime(LocalDateTime.now())
                .build();
    }

    private HashMap buildStatistic() {
        final var statistics = new HashMap<>();
        final var statistic = Statistic.builder()
                .month("JUNE")
                .totalBookCount(5)
                .totalOrderCount(2)
                .totalPurchasedAmount(52.0)
                .build();
        statistics.put("JUNE", statistic);
        return statistics;
    }
}