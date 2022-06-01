package com.getir.bookretail.controller.dto.response;

import com.getir.bookretail.model.Order;
import lombok.*;

import java.util.ArrayList;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookResponse {

    private String bookId;
    private String bookName;
    private String author;
    private int stock;
}
