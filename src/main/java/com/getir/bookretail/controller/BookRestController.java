package com.getir.bookretail.controller;

import com.getir.bookretail.controller.dto.request.BookRestRequest;
import com.getir.bookretail.controller.dto.request.UpdateBookRestRequest;
import com.getir.bookretail.controller.dto.response.UpdateBookResponse;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("retail/rest")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @PostMapping(value = "book")
    public ResponseEntity<Book> registerBook(@Validated @RequestBody BookRestRequest request) {

        log.info("Request to create a new book with name : {} and author : {}", request.getBookName(), request.getAuthor());
        final var result = bookService.registerBook(request);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "book")
    public ResponseEntity<UpdateBookResponse> updateBookStock(@Validated @RequestBody UpdateBookRestRequest request) {

        final var bookId = request.getBookId();
        final var stock = request.getStock();
        log.info("Request to update stock of book with Id : {}", request.getBookId());
        final var result = bookService.updateBookStock(bookId, stock);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
