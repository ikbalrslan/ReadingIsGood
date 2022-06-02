package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.BookRestRequest;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author İkbal Arslan
 */
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void shouldRegisterBookSuccessfully() {
        when(bookRepository.getBooksByNameAndAuthor(anyString(), anyString())).thenReturn(Optional.empty());
        when(bookRepository.insert(buildBook())).thenReturn(buildBook());

        final var actual = bookService.registerBook(createRequest());
        assertEquals("Açlık", actual.getBookName());
        assertEquals("Knut Hamsun", actual.getAuthor());

        verify(bookRepository, times(1)).getBooksByNameAndAuthor(anyString(), anyString());
    }

    @Test
    void shouldUpdateBookStockSuccessfully() {
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(buildBook()));

        assertEquals(50, buildBook().getStock());
        final var actual = bookService.updateBookStock("1", 5);
        assertEquals(5, actual.getStock());

        verify(bookRepository, times(1)).findById(anyString());
        verify(bookRepository, times(1)).save(any());
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

    private BookRestRequest createRequest() {
        final var request = new BookRestRequest();
        request.setBookName("Açlık");
        request.setTotalPage(250);
        request.setAuthor("Knut Hamsun");
        request.setGenre("dram");
        request.setStock(50);
        request.setPrice(34.33);
        return request;
    }
}