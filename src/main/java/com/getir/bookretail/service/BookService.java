package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.BookRestRequest;
import com.getir.bookretail.controller.dto.response.UpdateBookResponse;
import com.getir.bookretail.model.Book;

/**
 * @author İkbal Arslan
 */
public interface BookService {

    Book registerBook(BookRestRequest request);

    UpdateBookResponse updateBookStock(String bookId, int stock);
}
