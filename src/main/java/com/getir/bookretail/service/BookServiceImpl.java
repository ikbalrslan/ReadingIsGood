package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.BookRestRequest;
import com.getir.bookretail.controller.dto.response.CustomerOrdersResponse;
import com.getir.bookretail.controller.dto.response.UpdateBookResponse;
import com.getir.bookretail.fault.ResourceAlreadyExistException;
import com.getir.bookretail.fault.ResourceNotFoundException;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Order;
import com.getir.bookretail.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author İkbal Arslan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Book registerBook(BookRestRequest request) {
        final var book = bookRepository.getBooksByNameAndAuthor(request.getBookName(), request.getAuthor());
        if (book.isEmpty()) {
            final var bookInstance = buildBook(request);
            return bookRepository.insert(bookInstance);
        } else {
            throw new ResourceAlreadyExistException("Book already exist with name : " + book.get().getBookName() + " and author: " + book.get().getAuthor());
        }
    }

    @Override
    @Transactional
    public UpdateBookResponse updateBookStock(String bookId, int stock) {
        final var bookInfo = bookRepository.findById(bookId);
        if (bookInfo.isPresent()) {
            bookInfo.get().setStock(stock);
            bookRepository.save(bookInfo.get());
            log.info("Stock number: {} set to book with bookId: {}", stock, bookId);
        } else {
            throw new ResourceNotFoundException("Book not found for bookId : " + bookId);
        }
        return convertToResponse(bookInfo);
    }

    private UpdateBookResponse convertToResponse(Optional<Book> book) {
        final var response = new UpdateBookResponse();
        response.setBookId(book.get().getId());
        response.setBookName(book.get().getBookName());
        response.setAuthor(book.get().getAuthor());
        response.setStock(book.get().getStock());
        return response;
    }

    private Book buildBook(BookRestRequest request) {
        return Book.builder()
                .bookName(request.getBookName())
                .totalPage(request.getTotalPage())
                .author(request.getAuthor())
                .genre(request.getGenre())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }
}
