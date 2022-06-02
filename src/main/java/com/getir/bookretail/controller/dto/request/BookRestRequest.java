package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private Integer totalPage;
    @NotEmpty(message = "This field is required")
    private String author;
    @NotEmpty(message = "This field is required")
    private String genre;
    @NotNull(message = "This field is required")
    private Integer stock;
    @NotNull(message = "This field is required")
    private Double price;
}
