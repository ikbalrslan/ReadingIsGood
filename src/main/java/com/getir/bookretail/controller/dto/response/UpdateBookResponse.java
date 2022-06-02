package com.getir.bookretail.controller.dto.response;

import lombok.*;

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
