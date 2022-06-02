package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NegativeOrZero;
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
public class OrderRestRequest {
    @NotEmpty(message = "This field is required")
    private String customerId;
    @NotEmpty(message = "This field is required")
    private String bookId;
    @NotNull(message = "This field is required")
    @Positive(message = "Book order count must be positive")
    private Integer count;
    @NotNull(message = "This field is required")
    private Integer status;
}
