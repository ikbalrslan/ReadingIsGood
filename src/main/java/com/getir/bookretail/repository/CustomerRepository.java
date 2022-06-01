package com.getir.bookretail.repository;

import com.getir.bookretail.model.Customer;
import com.getir.bookretail.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author İkbal Arslan
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query("{email :?0}")
    Optional<Customer> getCustomerByEmail(String email);
}
