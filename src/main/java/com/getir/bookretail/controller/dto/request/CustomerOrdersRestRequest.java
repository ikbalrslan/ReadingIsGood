package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

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
    private int page;
    private int size;
}
