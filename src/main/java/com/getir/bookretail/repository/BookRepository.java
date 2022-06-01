package com.getir.bookretail.repository;

import com.getir.bookretail.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author İkbal Arslan
 */
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{bookName :?0}")
    Optional<Book> getBookByName(String bookName);

    @Query("{author : ?0}")
    List<Book> getBooksByAuthor(String author);

    @Query("{'bookName': ?0, 'author': ?1}")
    Optional<Book> getBooksByNameAndAuthor(String bookName, String author);
}
