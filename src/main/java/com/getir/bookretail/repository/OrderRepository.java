package com.getir.bookretail.repository;

import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author İkbal Arslan
 */
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{'customerId': ?0, 'bookId': ?1}")
    Optional<Order> getOrdersByCustomerIdAndBookId(String customerId, String bookId);

    @Query("{customerId :?0}")
    Page<Order> getOrdersByCustomerId(String customerId, Pageable pageable);

    @Query("{orderTime : { $gte: ?0, $lte: ?1 } }")
    List<Order> getOrdersByDate(LocalDateTime startDate, LocalDateTime endDate);
}
