package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookRestRequest {
    @NotEmpty(message = "This field is required")
    private String bookName;
    @NotNull(message = "This field is required")
    @Positive(message = "Total page must be positive")
    private Integer totalPage;
    @NotEmpty(message = "This field is required")
    private String author;
    @NotEmpty(message = "This field is required")
    private String genre;
    @NotNull(message = "This field is required")
    @Positive(message = "Stock number must be positive")
    private Integer stock;
    @NotNull(message = "This field is required")
    @Positive(message = "Price must be positive")
    private Double price;
}
