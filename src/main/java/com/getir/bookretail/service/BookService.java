package com.getir.bookretail.service;

import com.getir.bookretail.controller.dto.request.BookRestRequest;
import com.getir.bookretail.controller.dto.request.CustomerRestRequest;
import com.getir.bookretail.controller.dto.response.UpdateBookResponse;
import com.getir.bookretail.model.Book;
import com.getir.bookretail.model.Customer;

/**
 * @author İkbal Arslan
 */
public interface BookService {

    Book registerBook(BookRestRequest request);

    UpdateBookResponse updateBookStock(String bookId, int stock);
}
