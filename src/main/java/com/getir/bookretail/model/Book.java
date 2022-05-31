package com.getir.bookretail.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author İkbal Arslan
 */

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Books")
public class Book {
    @Id
    private String id;
    private String bookName;
    private int totalPage;
    private String author;
    private String genre;
    private int stock;
    private double price;
}
