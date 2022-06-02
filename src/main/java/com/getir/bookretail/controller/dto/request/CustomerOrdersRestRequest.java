package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrdersRestRequest {
    @NotEmpty(message = "This field is required")
    private String customerId;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Page must be positive or zero")
    private Integer page;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Size must be positive or zero")
    private Integer size;
}
